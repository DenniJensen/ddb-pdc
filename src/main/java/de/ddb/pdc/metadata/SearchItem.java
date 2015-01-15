package de.ddb.pdc.metadata;

/**
 * Class returned by a search with the MetaFetcher
 */
public class SearchItem {

  private int maxSearchResults;
  private DDBItem[] ddbItems;

  /**
   * Create a SearchItem with the max of results and list of DDBItems
   *
   * @param maxSearchResults int of max results
   * @param ddbItems list of DDBItems
   */
  public SearchItem(int maxSearchResults, DDBItem[] ddbItems) {
    this.maxSearchResults = maxSearchResults;
    this.ddbItems = ddbItems;
  }

  /**
   * Returns max results of the search
   */
  public int getMaxSearchResults() {
    return this.maxSearchResults;
  }

  /**
   * Returns list of DDBItem of the search
   */
  public DDBItem[] getDdbItems() {
    return this.ddbItems;
  }
}
