package de.ddb.pdc.crawler;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * The crawler is a command line application that calculates the public domain
 * status of DDBItems starting with a search term (for now '*'). It will
 * log all errors that occur during calculation of these DDBItems.
 */
@Component
@SuppressWarnings("nls")
public class Crawler implements CommandLineRunner {

  private static final Log LOG = LogFactory.getLog(Crawler.class);
  private boolean enabled = false;
  private int maxDepth = 1000;
  private int fetchSize = 100;
  private final int timeout = 500;
  private final CrawlerSchedule schedule;

  /**
   * Creates a new Crawler.
   */
  @Autowired
  public Crawler(CrawlerSchedule schedule) {
    this.schedule = schedule;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run(String... args) throws Exception {
    List<String> argsList = Arrays.asList(args);

    enabled = this.isArgument(argsList, "-c", "--crawler");

    if (!enabled) {
      LOG.info("The crawler is disabled. Enable it with --crawler or -c.");
      return;
    }

    String maxDepthString = getArgumentValue(argsList, "--depth");
    String fetchSizeString = getArgumentValue(argsList, "--fetchSize");

    if (maxDepthString != null) {
      try {
        maxDepth = Integer.parseInt(maxDepthString);
      } catch (NumberFormatException e) {
        // do nothing, keep the default maxDepth
      }
    }

    if (fetchSizeString != null) {
      try {
        fetchSize = Integer.parseInt(fetchSizeString);
      } catch (NumberFormatException e) {
        // do nothing, keep the default fetchSize
      }
    }

    LOG.info(String.format("The crawler is enabled. Max depth: %d, fetch size: "
        + "%d", maxDepth, fetchSize));

    schedule.setTimeout(timeout);
    schedule.setMaxDepth(maxDepth);
    schedule.setFetchSize(fetchSize);
    schedule.start();
  }

  /**
   * Check if at least one of the arguments given in cmds is present in the
   * command line arguments given at program start.
   * @param args The command line arguments.
   * @param cmds The command line switches to search for.
   * @return true if at least one of cmds command line switches is present in
   *         the arguments. false otherwise.
   */
  @SuppressWarnings("static-method")
  private boolean isArgument(List<String> args, String... cmds) {
    for (String cmd : cmds) {
      if (args.contains(cmd)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Search for a command line argument and return the value given to this
   * parameter.
   * @param args The command line arguments.
   * @param cmd The commnd line argument to return the value for.
   * @return The value as String or null if no value was found.
   */
  @SuppressWarnings("static-method")
  private String getArgumentValue(List<String> args, String cmd) {
    boolean found = false;
    for (String arg : args) {
      if (arg.equalsIgnoreCase(cmd)) {
        found =  true;
      } else if (found) {
        return arg;
      }
    }
    return null;
  }
}
