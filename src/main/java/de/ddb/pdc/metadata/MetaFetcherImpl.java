package de.ddb.pdc.metadata;

import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.xml.xpath.Jaxp13XPathTemplate;
import org.springframework.xml.xpath.XPathOperations;

import javax.xml.transform.dom.DOMSource;
import java.util.HashMap;
import java.util.Map;


/**
 * Implementation of the {@link MetaFetcher} interface.
 */
public class MetaFetcherImpl implements MetaFetcher {

  private static final String URL =
      "https://www.deutsche-digitale-bibliothek.de";

  private RestTemplate restTemplate;
  private String apiKey;

  private XPathOperations xpathTemplate;

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

    initNamespaces();
  }

  /**
   * initialize the namespaces for xpath
   */
  private void initNamespaces() {
    Map<String,String> namespaces = new HashMap<String,String>();
    namespaces.put("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
    namespaces.put("gndo", "http://d-nb.info/standards/elementset/gnd#");
    namespaces.put("ctx", "http://www.deutsche-digitale-bibliothek.de/cortex");
    namespaces.put("ns2", "http://www.deutsche-digitale-bibliothek.de/institution");
    namespaces.put("ns3", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
    namespaces.put("ns4", "http://www.deutsche-digitale-bibliothek.de/item");
    namespaces.put("ore", "http://www.openarchives.org/ore/terms/");
    namespaces.put("edm", "http://www.europeana.eu/schemas/edm/");
    namespaces.put("skos", "http://www.w3.org/2004/02/skos/core#");
    namespaces.put("dc", "http://purl.org/dc/elements/1.1/" );
    namespaces.put("dcterms", "http://purl.org/dc/terms/");
    Jaxp13XPathTemplate jaxp13XPathTemplate = new Jaxp13XPathTemplate();
    jaxp13XPathTemplate.setNamespaces(namespaces);
    this.xpathTemplate = jaxp13XPathTemplate;
  }

  /**
   * {@inheritDoc}
   */
  public SearchItems searchForItems(String query, int startItem, int maxCount,
      String sort) throws RestClientException {
    String url = ApiUrls.searchUrl(query, startItem, maxCount, sort, apiKey);
    SearchResults results = restTemplate.getForObject(url, SearchResults.class);
    return getDDBItems(results);
  }

  private SearchItems getDDBItems(SearchResults results) {
    if (results.getResultItems() == null) {
      return new SearchItems(0, new DDBItem[0]);
    }
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

    return new SearchItems(results.getNumberOfResults(), ddbItems);
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
    DOMSource domSource = restTemplate.getForObject(url, DOMSource.class);
    if (domSource != null) {
      fillDDBItem(ddbItem, domSource);
      if (ddbItem.getCclicense() == 0) {
        fetchAuthorMetadata(ddbItem);
      }
    }
    return ddbItem;
  }

  private void fillDDBItem(DDBItem item, DOMSource domSource) {
    ItemAipXml itemAipXml = new ItemAipXml(domSource, xpathTemplate);
    item.setTitle(itemAipXml.getTitle());
    item.setSubtitle(itemAipXml.getSubtitle());
    item.setImageUrl(URL + itemAipXml.getThumbnail());
    item.setPublishedYear(itemAipXml.getPublishedYear());
    item.setInstitution(itemAipXml.getInstitution());
    item.setCclicense(itemAipXml.getCCLicense());
    for (String authorId : itemAipXml.getAuthorUrls()) {
      Author author = new Author(authorId);
      item.addAuthor(author);
    }
  }

  private void fetchAuthorMetadata(DDBItem item) {
    for (Author author : item.getAuthors()) {
      String dnbUrl = ApiUrls.dnbUrl(author.getDnbId());
      DOMSource domSource = restTemplate.getForObject(dnbUrl, DOMSource.class);
      fillAuthor(author, domSource);
    }
  }

  private void fillAuthor(Author author, DOMSource domSource) {
    if (domSource != null) {
      ItemDnbAuthorXml idax = new ItemDnbAuthorXml(domSource, xpathTemplate);
      author.setName(idax.getName());
      author.setDateOfBirth(idax.getDateOfBirth());
      author.setDateOfDeath(idax.getDateOfDeath());

      DOMSource location = null;
      String dnbLocationUrl;
      String placeOfBirth = idax.getPlaceOfBirth();
      if (placeOfBirth == null || placeOfBirth.equals("")) {
        String placeOfDeath = idax.getPlaceOfDeath();
        if (placeOfDeath != null && !placeOfDeath.equals("")) {
          dnbLocationUrl = ApiUrls.dnbUrl(placeOfDeath);
          location = restTemplate.getForObject(dnbLocationUrl, DOMSource.class);
        }
      } else {
        dnbLocationUrl = ApiUrls.dnbUrl(placeOfBirth);
        location = restTemplate.getForObject(dnbLocationUrl, DOMSource.class);
      }

      if (location != null) {
        ItemDnbLocationXml idlx = new ItemDnbLocationXml(location,
            xpathTemplate);
        author.setNationality(idlx.getIso2CountryCode());
      } else {
        author.setNationality(null);
      }
    }
  }
}
