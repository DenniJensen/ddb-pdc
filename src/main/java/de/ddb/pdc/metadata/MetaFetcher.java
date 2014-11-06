package de.ddb.pdc.metadata;

public interface MetaFetcher {

	public DDBItem[] getSearchResult(String query, int max_count);
	public void getMetaData(DDBItem ddbItem);
}
