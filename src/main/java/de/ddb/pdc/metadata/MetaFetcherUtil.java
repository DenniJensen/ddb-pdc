package de.ddb.pdc.metadata;

/**
 * 
 * utility function for the MetaFetcher
 *
 */
public class MetaFetcherUtil {

  /**
   * @param string
   * @return string with replaced match string
   */
  public static String deleteMatch(String string) {
    return string.replace("<match>", "").replace("</match>", "");
  }
}
