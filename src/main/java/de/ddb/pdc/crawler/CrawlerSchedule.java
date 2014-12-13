package de.ddb.pdc.crawler;

import java.util.LinkedList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import de.ddb.pdc.core.PDCResult;
import de.ddb.pdc.core.PublicDomainCalculator;
import de.ddb.pdc.metadata.DDBItem;
import de.ddb.pdc.metadata.MetaFetcher;

/**
 * The crawler schedule does the actual crawling and calculating of DDBItems.
 * It will schedule its actions according to the maxDepth, fetchSize and offset
 * given to it.
 */
@Service
@SuppressWarnings("nls")
public class CrawlerSchedule extends Thread {

  @Value("${ddb.country:de}")
  private String country;

  private static final Log LOG = LogFactory.getLog(CrawlerSchedule.class);
  private int maxDepth;
  private int fetchSize;
  private int offset;
  private long timeout;
  private boolean end;
  private final LinkedList<DDBItem> fetchedResults;
  private final MetaFetcher metaFetcher;
  private final PublicDomainCalculator calculator;

  /**
   * Creates a new CrawlerSchedule.
   */
  @Autowired
  public CrawlerSchedule(MetaFetcher metaFetcher,
      PublicDomainCalculator calculator) {
    super("CrawlerSchedule");
    this.metaFetcher = metaFetcher;
    this.calculator = calculator;
    this.fetchedResults = new LinkedList<DDBItem>();
    this.offset = 0;
    this.end = false;
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
  }

  /**
   * In the fetch phase new items will be fetched from the DDB-API using
   * the searchForItems method of the controller.
   */
  private void runFetchPhase() {
    if (offset >= maxDepth) {
      end = true;
      LOG.info("Maximum crawling depth was reached.");
      return;
    }

    if (offset != 0) {
      end = true;
      LOG.warn("Shutting down crawler because paginated search is not possible "
          + "yet");
      // TODO: edit this when paginated search is possible
      return;
    }

    LOG.info(String.format("Running fetch phase from item %d to item %d",
        offset, offset + fetchSize));
    try {
      DDBItem[] results = metaFetcher.searchForItems("*", 0, fetchSize,
        "relevance");
      for (DDBItem result : results) {
        fetchedResults.add(result);
      }
    } catch (Throwable e) {
      LOG.error("Error while fetching results", e);
    }
    offset += fetchSize;
  }

  /**
   * In the calculate phase the public domain status of fetched items
   * is calculated.
   */
  private void runCalculatePhase() {
    DDBItem item = fetchedResults.pop();
    String id = item.getId();

    LOG.info(String.format("Running calculate phase for item %s", id));
    try {
      PDCResult result = calculator.calculate(country, item);
      LOG.info(String.format("Calculation successful for %s. Result: %s",
          id, result.isPublicDomain()));
    } catch (Throwable e) {
      LOG.error(String.format("Calculation unsuccessful for %s.", id));
      LOG.error("Here is the trace:", e);
    }
  }
}
