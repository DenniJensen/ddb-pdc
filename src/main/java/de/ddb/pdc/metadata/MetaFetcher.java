package de.ddb.pdc.metadata;

import org.springframework.web.client.RestClientException;

/**
 * Interface for retrieving metadata about items in the DDB database.
 */
public interface MetaFetcher {

  /**
   * Searches the DDB for items whose metadata contains the passed substring.
   *
   * @param query                substring to search for
   * @param startItem            start number of items
   * @param maxCount             maximum number of items to return
   * @param sort                 sort order of items
   * @return                     matching items
   * @throws RestClientException if communication with the DDB API fails
   */
  public SearchItem searchForItems(String query, int startItem, int maxCount,
      String sort) throws RestClientException;

  /**
   * Fills the passed {@link DDBItem} with all metadata available in the
   * DDB database.
   *
   * @param itemId               ID of item to fill
   * @return                     the item
   * @throws RestClientException if communication with the DDB API fails
   */
  public DDBItem fetchMetadata(String itemId) throws RestClientException;
}
