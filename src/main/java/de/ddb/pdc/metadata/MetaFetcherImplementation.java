package de.ddb.pdc.metadata;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.springframework.web.client.RestTemplate;

/**
 * 
 * Implementation of a {@link MetaFetcher}
 *
 */
public class MetaFetcherImplementation implements MetaFetcher {

  private final String URL = "https://www.deutsche-digitale-bibliothek.de";
  private final String APIURL = "https://api.deutsche-digitale-bibliothek.de";
  private final String SEARCH = "/search?";
  private final String ITEMS = "/items/";
  private final String AUTH = "oauth_consumer_key=";
  private final String SORTROWS = "&sort=RELEVANCE&rows=";
  private final String QUERY = "&query=";
  private final String EDM = "/edm?";
	
	private String authKey="";
	
	/** 
	 * @param authKey authentication Key for the DDB API
	 */
	public MetaFetcherImplementation(String authKey) {
		this.authKey = authKey;
	}
	
	/**
	 * @param query 
	 * @param max_count
	 * @return array of DDBItems with the search result
	 */
	public DDBItem[] getSearchResult(String query, int max_count) {
		RestTemplate restTemplate = new RestTemplate();
		String modifiedQuery = query.replace(" ", "+");
		String url = APIURL+SEARCH+AUTH+authKey+SORTROWS+max_count+QUERY
				+modifiedQuery;
		JSONResult jsonResult = restTemplate
				.getForObject(url, JSONResult.class);
		return getDDBItems(jsonResult);
	}
	

	/**
	 * @param ddbItem for the id to get the meta data
	 */
	public void getMetaData(DDBItem ddbItem) {
		
	}
	
	/**
	 * @param jsonresult
	 * @return array filled with search results
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private DDBItem[] getDDBItems(JSONResult jsonresult) {
	  ArrayList<LinkedHashMap> results = (ArrayList<LinkedHashMap>) jsonresult
			  .getResults().get(0).get("docs");
	  int max_results = results.size();
	  DDBItem[] ddbItems = new DDBItem[max_results];
		
	  for (int i = 0; i < max_results; i++) {
	    DDBItem ddbItem = new DDBItem((String) results.get(i).get("id"));
		ddbItem.setTitle(MetaFetcherUtil.deleteMatch((String) results.get(i)
				.get("title")));
	    ddbItem.setSubtitle(MetaFetcherUtil.deleteMatch((String) results
	    		.get(i).get("subtitle")));
		ddbItem.setImgUrl(URL+(String) results.get(i).get("thumbnail"));
	    ddbItems[i] = ddbItem;
	  }
		
	  return ddbItems;
	}

}
