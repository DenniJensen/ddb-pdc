package de.ddb.pdc.metadata;

import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation of the {@link MetaFetcher} interface.
 */
public class MetaFetcherImpl implements MetaFetcher {

  private static final String URL =
    "https://www.deutsche-digitale-bibliothek.de";

  private RestTemplate restTemplate;
  private String apiKey;

  /**
   * Creates a new object of the class MetaFetcherImpl.
   * This class collects the needed information for answering the questions.
   * The data about works and authors is collected by calls to the DDB API.
   *
   * @param restTemplate RestTemplate to use for issuing requests
   * @param apiKey       DDB API key for authentication
   */
  public MetaFetcherImpl(RestTemplate restTemplate, String apiKey) {
    this.restTemplate = restTemplate;
    this.restTemplate.getMessageConverters()
        .add(new Jaxb2RootElementHttpMessageConverter());
    this.apiKey = apiKey;
  }

  /**
   * {@inheritDoc}
   */
  public DDBItem[] searchForItems(String query, int startItem, int maxCount,
      String sort) throws RestClientException {
    String url = ApiUrls.searchUrl(query, startItem, maxCount, sort, apiKey);
    SearchResults results = restTemplate.getForObject(url, SearchResults.class);
    return getDDBItems(results);
  }

  private DDBItem[] getDDBItems(SearchResults results) {
    if (results.getResultItems() == null) {
      return new DDBItem[0];
    }
    int numItems = results.getResultItems().size();
    DDBItem[] ddbItems = new DDBItem[numItems];

    int idx = 0;
    for (SearchResultItem rsi : results.getResultItems()) {
      DDBItem ddbItem = new DDBItem(rsi.getId());
      ddbItem.setMaxResults(results.getNumberOfResults());
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

  private static String deleteMatchTags(String string) {
    return string.replace("<match>", "").replace("</match>", "");
  }

  /**
   * {@inheritDoc}
   */
  public DDBItem fetchMetadata(String itemId) throws RestClientException {
    DDBItem ddbItem = new DDBItem(itemId);
    String url = ApiUrls.itemAipUrl(itemId, apiKey);
    ItemAipResult result = restTemplate.getForObject(url, ItemAipResult.class);
    if (result.getRDFItem() != null) {
      fillDDBItem(ddbItem, result);
      fetchAuthorMetadata(ddbItem);
    }
    return ddbItem;
  }

  private void fillDDBItem(DDBItem item, ItemAipResult result) {
    RDFItem rdf = result.getRDFItem();
    item.setPublishedYear(rdf.getPublishYear());
    item.setInstitution(rdf.getInstitution());

    for (String authorId : rdf.getAuthorIds()) {
      Author author = new Author(authorId);
      item.addAuthor(author);
    }
  }

  private void fetchAuthorMetadata(DDBItem item) {
    for (Author author : item.getAuthors()) {
      String dnbUrl = ApiUrls.dnbUrl(author.getDnbId());
      DNBAuthorItem result = restTemplate.getForObject(dnbUrl,
          DNBAuthorItem.class);
      fillAuthor(author, result);

    }
  }

  private void fillAuthor(Author author, DNBAuthorItem result) {
    if (result != null) {
      author.setName(result.name());
      author.setDateOfBirth(result.dateOfBirth());
      author.setDateOfDeath(result.dateOfDeath());
      if (result.placeOfDeathUri() != null) {
        String dnbUrl = ApiUrls.dnbUrl(result.placeOfDeathUri());
        DNBLocationItem location = restTemplate.getForObject(dnbUrl,
            DNBLocationItem.class);
        if (location != null) {
          author.setPlaceOfBirth(location.locate());
        }
      }
    }
  }
}
