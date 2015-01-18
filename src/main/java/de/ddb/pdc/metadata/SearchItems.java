package de.ddb.pdc.metadata;

/**
 * Class for object which returned by a search with the MetaFetcher.
 */
public class SearchItems {

  private int maxSearchResults;
  private DDBItem[] ddbItems;

  /**
   * Create a SearchItems with the max of results and list of DDBItems.
   *
   * @param maxSearchResults int of max results
   * @param ddbItems list of DDBItems
   */
  public SearchItems(int maxSearchResults, DDBItem[] ddbItems) {
    this.maxSearchResults = maxSearchResults;
    this.ddbItems = ddbItems;
  }

  /**
   * Returns the total number of items which matched the search
   * query. This is different from the number of items in the
   * SearchResults object, which is maximally as large as the number
   * of items requested by the client. Thus, the return value of
   * this method can be used to tell if there are more matching
   * items, and how many.
   */
  public int getMaxSearchResults() {
    return this.maxSearchResults;
  }

  /**
   * Returns list of DDBItem of the search.
   */
  public DDBItem[] getDdbItems() {
    return this.ddbItems;
  }
}
