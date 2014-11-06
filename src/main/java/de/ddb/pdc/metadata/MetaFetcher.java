package de.ddb.pdc.metadata;

public interface MetaFetcher {

  public DDBItem[] getSearchResult(String query, int maxCount);
  public void getMetaData(DDBItem ddbItem);
}
