package de.ddb.pdc.metadata;

import org.springframework.web.client.RestClientException;

/**
 * Interface of the MetaFetcher
 */
public interface MetaFetcher {

  /**
   * @param query what to search
   * @param maxCount how many items will be search
   * @return Array of DDBItems with search result
   * @throws RestClientException
   */
  public DDBItem[] searchForItems(String query, int maxCount) throws RestClientException;
  
  /**
   * @param ddbItem used to get metadata with the id of the DDBItem
   * @throws RestClientException
   */
  public void fetchMetadata(DDBItem ddbItem) throws RestClientException;
}
