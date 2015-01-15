package de.ddb.pdc.crawler;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import de.ddb.pdc.metadata.SearchItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sun.org.apache.xpath.internal.FoundIndex;

import de.ddb.pdc.core.AnsweredQuestion;
import de.ddb.pdc.core.PDCResult;
import de.ddb.pdc.core.PublicDomainCalculator;
import de.ddb.pdc.core.Question;
import de.ddb.pdc.metadata.DDBItem;
import de.ddb.pdc.metadata.MetaFetcher;
import de.ddb.pdc.web.PDCController;
import de.ddb.pdc.web.SearchController;

/**
 * The crawler schedule does the actual crawling and calculating of DDBItems.
 * It will schedule its actions according to the maxDepth, fetchSize and offset
 * given to it.
 */
@Service
@SuppressWarnings("nls")
public class CrawlerSchedule extends Thread {

  private static final Logger LOG = LoggerFactory.getLogger(
      CrawlerSchedule.class);
  private int maxDepth;
  private int fetchSize;
  private LinkedList<Integer> paginationOffsets = new LinkedList<Integer>();
  private long timeout;
  private boolean end;
  private final List<DDBItem> fetchedResults = new LinkedList<>();
  private final SearchController searchController;
  private final PDCController pdcController;
  private final Map<Question, Integer> unknownCauses;
  private final Set<String> foundCategories;
  private int depthCount;
  private int countTrue;
  private int countFalse;
  private int countFailed;
  private int countUnknown;

  /**
   * Creates a new CrawlerSchedule.
   */
  @Autowired
  public CrawlerSchedule(SearchController searchController, 
                         PDCController pdcController) {
    super("CrawlerSchedule");
    this.searchController = searchController;
    this.pdcController = pdcController;

    this.unknownCauses = new TreeMap<Question, Integer>();
    this.foundCategories = new TreeSet<String>();
  }

  /**
   * Set the maximum crawling depth.
   * @param maxDepth The maximum crawling depth.
   */
  public void setMaxDepth(int maxDepth) {
    this.maxDepth = maxDepth;
  }

  /**
   * Set the fetch size. This is the amount of items that are fetched
   * in one fetch phase.
   * @param fetchSize The fetch size.
   */
  public void setFetchSize(int fetchSize) {
    this.fetchSize = fetchSize;
  }

  /**
   * Set the timeout in milliseconds between the actions of the crawler.
   * @param timeout The timeout in milliseconds.
   */
  public void setTimeout(int timeout) {
    this.timeout = timeout;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run() {
    randomizePaginationOffsets();
    while (!end) {
      if (fetchedResults.isEmpty()) {
        runFetchPhase();
      } else {
        runCalculatePhase();
      }
      Thread.yield();

      try {
        Thread.sleep(timeout);
      } catch (InterruptedException e) {
        // we were interrupted, cancel the crawler thread
        end = true;
      }
    }
    LOG.info("Crawler schedule complete.");
    printStatistics();
  }

  private void randomizePaginationOffsets() {
    int numberOfSearches = (int) Math.ceil(this.maxDepth / this.fetchSize);
    Random random = new Random();
    while (numberOfSearches-- > 0) {
      int offset = 0;
      do {
        offset = random.nextInt(this.maxDepth) * this.fetchSize;
      } while (paginationOffsets.contains(offset));
      paginationOffsets.add(offset);
    }
  }

  /**
   * In the fetch phase new items will be fetched from the DDB-API using
   * the searchForItems method of the controller.
   */
  private void runFetchPhase() {
    if (depthCount >= maxDepth) {
      end = true;
      LOG.info("Maximum crawling depth was reached.");
      return;
    }
    
    int offset = paginationOffsets.pop();

    LOG.info(String.format("Running fetch phase from item %d to item %d",
        offset, offset + fetchSize));
    try {

      SearchItem searchItem = searchController.search("*", fetchSize, offset);
      for (DDBItem result : searchItem.getDdbItems()) {
        fetchedResults.add(result);
      }
    } catch (Exception e) {
      LOG.error("Error while fetching results", e);
    }
  }

  /**
   * In the calculate phase the public domain status of fetched items
   * is calculated.
   */
  private void runCalculatePhase() {
    DDBItem item = fetchedResults.remove(0);
    String id = item.getId();
    String category = item.getCategory();
    foundCategories.add(category);

    LOG.info("Running calculate phase for item {}", id);
    try {
      PDCResult result = pdcController.determinePublicDomain(id);
      LOG.info("Calculation successful for {}. Result: {}", id, 
          result.isPublicDomain());
      
      if (result.isPublicDomain() == null) {
        Question question = findUnansweredQuestion(result);
        LOG.info("Result unknown due to missing answer for Question {}", 
            question);
        countUnknownQuestion(question);
      } else if (result.isPublicDomain()) {
        countTrue ++;
      } else {
        countFalse ++;
      }
    } catch (Exception e) {
      LOG.error("Calculation unsuccessful for {}", id);
      LOG.error("Here is the trace:", e);
      countFailed++;
    } finally {
      depthCount++;
    }
  }
  
  private Question findUnansweredQuestion(PDCResult result) {
    List<AnsweredQuestion> answeredQuestions = result.getTrace();
    AnsweredQuestion lastAnsweredQuestion = answeredQuestions
        .get(answeredQuestions.size() - 1);
    return lastAnsweredQuestion.getQuestion();
  }
  
  private void countUnknownQuestion(Question question) {
    if (!unknownCauses.containsKey(question)) {
      unknownCauses.put(question, 1);
    } else {
      unknownCauses.put(question, unknownCauses.get(question) + 1);
    }
    countUnknown++;
  }
  
  private void printStatistics() {
    LOG.info("================= CRAWLER STATISTICS =================");
    LOG.info(String.format("Crawler crawled %d Items.", this.depthCount));
    LOG.info(String.format("%d items are public domain", this.countTrue));
    LOG.info(String.format("%d items are not public domain", this.countFalse));
    LOG.info(String.format("%d items are unknown", this.countUnknown));
    LOG.info(String.format("%d items failed", this.countFailed));
    
    for (Entry<Question, Integer> entry : unknownCauses.entrySet()) {
      Question question = entry.getKey();
      int amount = entry.getValue();
      LOG.info("Items unknown due to question {}: {}", question, amount);
    }
    
    LOG.info("Found the following categories:");
    Iterator<String> categoryIterator = foundCategories.iterator();
    while (categoryIterator.hasNext()) {
      String category = categoryIterator.next();
      LOG.info(category);
    }
  }
}
