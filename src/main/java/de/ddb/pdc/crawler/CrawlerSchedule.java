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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

  private static final Log LOG = LogFactory.getLog(CrawlerSchedule.class);
  private int maxDepth;
  private int fetchSize;
  private LinkedList<Integer> offsets;
  private long timeout;
  private boolean end;
  private final LinkedList<DDBItem> fetchedResults;
  private final SearchController searchController;
  private final PDCController pdcController;
  private final Map<Question, Integer> unknownCauses;
  private final Set<String> foundCategories;
  private int count;
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
    this.fetchedResults = new LinkedList<DDBItem>();
    this.offsets = new LinkedList<Integer>();
    this.end = false;
    this.count = 0;

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
    randomizeOffsets();
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

  private void randomizeOffsets() {
    int numberOfSearches = (int) Math.ceil(this.maxDepth / this.fetchSize);
    Random random = new Random();
    while (numberOfSearches-- > 0) {
      int offset = 0;
      do {
        offset = random.nextInt(this.maxDepth) * this.fetchSize;
      } while(offsets.contains(offset));
      offsets.add(offset);
    }
  }

  /**
   * In the fetch phase new items will be fetched from the DDB-API using
   * the searchForItems method of the controller.
   */
  private void runFetchPhase() {
    if (count >= maxDepth) {
      end = true;
      LOG.info("Maximum crawling depth was reached.");
      return;
    }
    
    int offset = offsets.pop();

    LOG.info(String.format("Running fetch phase from item %d to item %d",
        offset, offset + fetchSize));
    try {
      DDBItem[] results = searchController.search("*", fetchSize, offset);
      for (DDBItem result : results) {
        fetchedResults.add(result);
      }
    } catch (Throwable e) {
      LOG.error("Error while fetching results", e);
    }
  }

  /**
   * In the calculate phase the public domain status of fetched items
   * is calculated.
   */
  private void runCalculatePhase() {
    DDBItem item = fetchedResults.pop();
    String id = item.getId();
    String category = item.getCategory();
    foundCategories.add(category);

    LOG.info(String.format("Running calculate phase for item %s", id));
    try {
      PDCResult result = pdcController.determinePublicDomain(id);
      LOG.info(String.format("Calculation successful for %s. Result: %s",
          id, result.isPublicDomain()));
      
      if (result.isPublicDomain() == null) {
        List<AnsweredQuestion> answeredQuestions = result.getTrace();
        AnsweredQuestion lastAnsweredQuestion = answeredQuestions
            .get(answeredQuestions.size() - 1);
        Question lastQuestion = lastAnsweredQuestion.getQuestion();
        String questionConstant = lastQuestion.toString();
        LOG.info(String.format("Result unknown due to missing answer "
            + "for Question %s", questionConstant));
        
        if (!unknownCauses.containsKey(lastQuestion)) {
          unknownCauses.put(lastQuestion, 1);
        } else {
          unknownCauses.put(lastQuestion, unknownCauses.get(lastQuestion) + 1);
        }
        countUnknown++;
      } else if (result.isPublicDomain()) {
        countTrue ++;
      } else {
        countFalse ++;
      }
    } catch (Throwable e) {
      LOG.error(String.format("Calculation unsuccessful for %s.", id));
      LOG.error("Here is the trace:", e);
      countFailed++;
    } finally {
      count++;
    }
  }
  
  private void printStatistics() {
    LOG.info("================= CRAWLER STATISTICS =================");
    LOG.info(String.format("Crawler crawled %d Items.", this.count));
    LOG.info(String.format("%d items are public domain", this.countTrue));
    LOG.info(String.format("%d items are not public domain", this.countFalse));
    LOG.info(String.format("%d items are unknown", this.countUnknown));
    LOG.info(String.format("%d items failed", this.countFailed));
    
    for (Entry<Question, Integer> entry : unknownCauses.entrySet()) {
      Question question = entry.getKey();
      int amount = entry.getValue();
      LOG.info(String.format("Items unknown due to question %s: %d", 
                             question, amount));
    }
    
    LOG.info("Found the following categories:");
    Iterator<String> categoryIterator = foundCategories.iterator();
    while (categoryIterator.hasNext()) {
      String category = categoryIterator.next();
      LOG.info(category);
    }
  }
}
