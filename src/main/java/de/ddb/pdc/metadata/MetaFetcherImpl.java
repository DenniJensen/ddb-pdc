package de.ddb.pdc.metadata;

import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.xml.xpath.Jaxp13XPathTemplate;
import org.springframework.xml.xpath.XPathOperations;
import org.w3c.dom.Node;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


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
   * initialize the namespaces for XPath
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
    DOMSource result = restTemplate.getForObject(url, DOMSource.class);
    if (result != null) {
      fillDDBItem(ddbItem, result);
      fetchAuthorMetadata(ddbItem);
    }
    return ddbItem;
  }

  private void fillDDBItem(DDBItem item, DOMSource result) {
    String xpathIssued = "//dcterms:issued";
    String xpathInstitution = "//edm:dataProvider[1]";
    item.setPublishedYear(getDateAsInt(xpathTemplate
        .evaluateAsString(xpathIssued, result),"\\d{4}"));
    item.setInstitution(xpathTemplate
        .evaluateAsString(xpathInstitution, result));
    for (String authorId : listAuthorIDs(result)) {
      Author author = new Author(authorId);
      item.addAuthor(author);
    }
  }

  private int getDateAsInt(String date, String regex) {
    return Integer.parseInt(MetadataUtils.useRegex(date, regex));
  }

  private List<String> listAuthorIDs(DOMSource domSource) {
    List<String> authorIDs = new ArrayList<String>();
    String xpathFacetRole =
        "//ctx:facet[@name='affiliate_fct_role_normdata']/ctx:value";
    String xpathFacet =
        "//ctx:facet[@name='affiliate_fct_normdata']/ctx:value";
    List<Node> nodes = xpathTemplate.evaluateAsNodeList(xpathFacetRole,
        domSource);
    for (Node node : nodes) {
      String value = node.getFirstChild().getTextContent();
      if (value.endsWith("_1_affiliate_fct_subject")
          || value.endsWith("_1_affiliate_fct_involved")) {
        authorIDs.add(value.split("_")[0]);
      }
    }
    if (authorIDs.size() == 0) {
      nodes = xpathTemplate.evaluateAsNodeList(xpathFacet, domSource);
      for (Node node : nodes) {
        authorIDs.add(node.getFirstChild().getTextContent());
      }
    }
    return authorIDs;
  }

  private void fetchAuthorMetadata(DDBItem item) {
    for (Author author : item.getAuthors()) {
      String dnbUrl = ApiUrls.dnbUrl(author.getDnbId());
      DOMSource result = restTemplate.getForObject(dnbUrl, DOMSource.class);
      fillAuthor(author, result);
    }
  }

  private void fillAuthor(Author author, Source result) {
    String xpathName = "//gndo:variantNameForThePerson";
    String xpathDOB  = "//gndo:dateOfBirth";
    String xpathDOD  = "//gndo:dateOfDeath";
    String xpathPOD  = "//gndo:placeOfDeath/rdf:Description/@rdf:about";
    String xpathLoc  = "//gndo:geographicAreaCode/@rdf:resource";
    if (result != null) {
      author.setName(xpathTemplate.evaluateAsString(xpathName, result));
      author.setDateOfBirth(formatDateString(xpathTemplate
          .evaluateAsString(xpathDOB, result)));
      author.setDateOfDeath(formatDateString(xpathTemplate
          .evaluateAsString(xpathDOD, result)));
      String placeOfDeath = xpathTemplate.evaluateAsString(xpathPOD, result);
      if (placeOfDeath != null) {
        String dnbUrl = ApiUrls.dnbUrl(placeOfDeath);
        DOMSource location = restTemplate.getForObject(dnbUrl,
            DOMSource.class);
        if (location != null) {
          author.setNationality(iso2Locate(xpathTemplate
              .evaluateAsString(xpathLoc, location)));
        }
      }
    }
  }

  private Calendar formatDateString(String date) {
    Calendar cal = null;
    String[] temp = date.split("-");
    if (temp.length == 3) {
      cal = new GregorianCalendar();
      cal.set(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),
          Integer.parseInt(temp[2]));
    }
    return cal;
  }

  private String iso2Locate(String location) {
    if (location != null) {
      String[] temp = location.split("#");
      String[] temp2 = temp[temp.length - 1].split("-");
      if (temp2.length > 2) {
        return temp2[1].toLowerCase();
      }
    }
    return null;
  }
}
