package de.ddb.pdc.metadata;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation of the {@link MetaFetcher} interface.
 */
public class MetaFetcherImpl implements MetaFetcher {

  private static final String URL =
      "https://www.deutsche-digitale-bibliothek.de";
  private static final String APIURL =
      "https://api.deutsche-digitale-bibliothek.de";
  private static final String SEARCH = "/search?";
  private static final String AUTH = "oauth_consumer_key=";
  private static final String SORTROWS = "&sort=RELEVANCE&rows=";
  private static final String QUERY = "&query=";

  private final RestTemplate restTemplate;
  private final String authKey;

  /**
   * Creates a new MetaFetcherImpl.
   *
   * @param restTemplate RestTemplate to use for issuing requests
   * @param authKey      authentication Key for the DDB API
   */
  public MetaFetcherImpl(RestTemplate restTemplate, String authKey) {
    this.restTemplate = restTemplate;
    this.authKey = authKey;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DDBItem[] searchForItems(String query, int maxCount)
      throws RestClientException {
    String modifiedQuery = query.replace(" ", "+");
    String url =
        MetaFetcherImpl.APIURL + MetaFetcherImpl.SEARCH + MetaFetcherImpl.AUTH
            + this.authKey + MetaFetcherImpl.SORTROWS + maxCount
            + MetaFetcherImpl.QUERY + modifiedQuery;
    ResultsOfJSON roj =
        this.restTemplate.getForObject(url, ResultsOfJSON.class);
    return this.getDDBItems(roj);
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void fetchMetadata(DDBItem ddbItem) throws RestClientException {
    // TODO: implement
  }

  private DDBItem[] getDDBItems(ResultsOfJSON roj) {
    int maxResults = roj.getResults().size();
    DDBItem[] ddbItems = new DDBItem[maxResults];

    int idx = 0;
    for (SearchResultItem rsi : roj.getResults()) {
      DDBItem ddbItem = new DDBItem(rsi.getId());
      System.out.println(rsi.getId());
      ddbItem.setTitle(MetaFetcherImpl.deleteMatchTags(rsi.getTitle()));
      ddbItem.setSubtitle(MetaFetcherImpl.deleteMatchTags(rsi.getSubtitle()));
      ddbItem.setImageUrl(MetaFetcherImpl.URL + rsi.getThumbnail());
      ddbItem.setCategory(rsi.getCategory());
      ddbItem.setMedia(rsi.getMedia());
      ddbItem.setType(rsi.getType());
      ddbItems[idx] = ddbItem;
      idx++;
    }

    return ddbItems;
  }

  /**
   * Deletes the "<match>...</match>" markers in metadata values of search
   * result items. These are added by the DDB API to simplify highlighting
   * of matching substrings, but we don't need or want them.
   */
  public static String deleteMatchTags(String string) {
    return string.replace("<match>", "").replace("</match>", "");
  }
}
