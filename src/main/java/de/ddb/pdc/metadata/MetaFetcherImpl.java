package de.ddb.pdc.metadata;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
/**
 * Implementation of the MetaFetcher Interface
 */
public class MetaFetcherImpl implements MetaFetcher {

  private static final String URL = 
    "https://www.deutsche-digitale-bibliothek.de";
  private static final String APIURL = 
    "https://api.deutsche-digitale-bibliothek.de";
  private static final String SEARCH = "/search?";
  private static final String ITEMS = "/items/";
  private static final String AUTH = "oauth_consumer_key=";
  private static final String SORTROWS = "&sort=RELEVANCE&rows=";
  private static final String QUERY = "&query=";
  private static final String EDM = "/edm?";

  private String authKey;

  /** 
   * @param authKey authentication Key for the DDB API
   */
  public MetaFetcherImpl(String authKey) {
    this.authKey = authKey;
  }

  /**
   * @param query 
   * @param maxCount
   * @return array of DDBItems with the search result
   */
  public DDBItem[] searchForItems(String query, int maxCount) throws RestClientException {
    RestTemplate restTemplate = new RestTemplate();
    String modifiedQuery = query.replace(" ", "+");
    String url = APIURL + SEARCH + AUTH + authKey + SORTROWS + maxCount + QUERY
      + modifiedQuery;
    JSONResult jsonResult = restTemplate.getForObject(url, JSONResult.class);
    return getDDBItems(jsonResult);
  }


  /**
   * @param ddbItem for the id to get the meta data
   */
  public void fetchMetadata(DDBItem ddbItem) throws RestClientException {

  }

  /**
   * @param jsonresult
   * @return array filled with search results
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  private DDBItem[] getDDBItems(JSONResult jsonresult) {
    ArrayList<LinkedHashMap> results = (ArrayList<LinkedHashMap>) jsonresult
      .getResults().get(0).get("docs");
    int maxResults = results.size();
    DDBItem[] ddbItems = new DDBItem[maxResults];
    
    for (int idx = 0; idx < maxResults; idx++) {
      DDBItem ddbItem = new DDBItem((String) results.get(idx).get("id"));
      ddbItem.setTitle(MetaFetcherUtil.deleteMatch((String) results.get(idx)
        .get("title")));
      ddbItem.setSubtitle(MetaFetcherUtil.deleteMatch((String) results
        .get(idx).get("subtitle")));
      ddbItem.setImageUrl(URL + (String) results.get(idx).get("thumbnail"));
      ddbItems[idx] = ddbItem;
    }

    return ddbItems;
  }
}
