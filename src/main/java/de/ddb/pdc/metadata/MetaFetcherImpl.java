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
  private static final String ITEM = "/items/";
  private static final String ENTITY = "/entities?query=id:\"";
  private static final String ENTITY_END = "\"&";
  private static final String AIP = "/aip?";

  private RestTemplate restTemplate;
  private String authKey;

  /**
   * Creates a new object of the class MetaFetcherImpl.
   * This class collects the needed information for answering the questions.
   * The data about works and authors is collected by calls to the DDB API.
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
  public DDBItem[] searchForItems(String query, int maxCount)
      throws RestClientException {
    String modifiedQuery = query.replace(" ", "+");
    String url = APIURL + SEARCH + AUTH + authKey + SORTROWS + maxCount + QUERY
      + modifiedQuery;
    SearchResults results = restTemplate.getForObject(url, SearchResults.class);
    return getDDBItems(results);
  }

  /**
   * Creates an array of objects containing search results from the DDB API.
   * 
   * @param results The original format of the search results
   * @return An array of DDBItem in a usable format for displaying
   */
  private DDBItem[] getDDBItems(SearchResults results) {
    int numItems = results.getResultItems().size();
    DDBItem[] ddbItems = new DDBItem[numItems];

    int idx = 0;
    for (SearchResultItem rsi : results.getResultItems()) {
      DDBItem ddbItem = new DDBItem(rsi.getId());
      ddbItem.setTitle(deleteMatchTags(rsi.getTitle()));
      ddbItem.setSubtitle(deleteMatchTags(rsi.getSubtitle()));
      ddbItem.setImageUrl(URL + rsi.getThumbnail());
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
   *
   * @param string The string containing the markers
   * @return A new string with the same content but without the markers
   */
  private static String deleteMatchTags(String string) {
    return string.replace("<match>", "").replace("</match>", "");
  }

  /**
   * {@inheritDoc}
   */
  public DDBItem fetchMetadata(String itemId) throws RestClientException {
    DDBItem ddbItem = new DDBItem(itemId);
    String url = APIURL + ITEM + ddbItem.getId() + AIP + AUTH + authKey;
    ItemAipResult result = restTemplate.getForObject(url, ItemAipResult.class);
    fillDDBItem(ddbItem, result);
    fetchAuthorMetadata(ddbItem);
    return ddbItem;
  }

  /**
   * Inserts more information about the work for each DDBItem (e.g. authors).
   * The data is extracted from RDFItem via ItemAipResult.
   * 
   * @param item The DDBItem object in which the data will be stored
   * @param result The object containing the result from the query to the DDB
   */
  private void fillDDBItem(DDBItem item, ItemAipResult result) {
    RDFItem rdf = result.getRDFItem();
    item.setPublishedYear(rdf.getPublishYear());
    item.setInstitution(rdf.getInstitution());

    for (String authorId : rdf.getAuthorIds()) {
      Author author = new Author(authorId);
      item.addAuthor(author);
    }
  }

  /**
   * Starts a query to the DDB API in order to get metadata about an author.
   * The data is then filled into the corresponding DDBItem of the work.
   * 
   * @param item The DDBItem which author's metadata will be updated
   */
  private void fetchAuthorMetadata(DDBItem item) {
    for (Author author : item.getAuthors()) {
      String urlEntity = APIURL + ENTITY + author.getDnbId() + ENTITY_END
          + AUTH + authKey;
      EntitiesResult result = restTemplate.getForObject(urlEntity,
          EntitiesResult.class);
      fillAuthor(author, result);
    }
  }

  /**
   * Inserts the metadata about an author into an author object.
   * 
   * @param author The author object in which the data will be stored
   * @param result The object containing the result from the query to the DDB
   */
  private void fillAuthor(Author author, EntitiesResult result) {
    EntitiesResultItem entity = result.getResultItem();
    author.setName(entity.getName());
    author.setYearOfBirth(entity.getYearOfBirth());
    author.setYearOfDeath(entity.getYearOfDeath());
    author.setPlaceOfBirth(entity.getPlaceOfBirth());
  }
}
