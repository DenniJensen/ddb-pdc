package de.ddb.pdc.metadata;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import org.springframework.xml.xpath.Jaxp13XPathTemplate;
import org.springframework.xml.xpath.XPathOperations;
import org.w3c.dom.Node;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MetaFetcherImplTest {

  private MetaFetcherImpl fetcher;
  private RestTemplate rest;
  private XPathOperations xpathTemplate;
  private DOMSource domSource;

  @Before
  public void setUp() {
    rest = mock(RestTemplate.class);
    fetcher = new MetaFetcherImpl(rest, "authkey");

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

    xpathTemplate = mock(jaxp13XPathTemplate.getClass());
    domSource = mock(DOMSource.class);
  }

  @Test
  public void search() {
    SearchResultItem resultItem = mock(SearchResultItem.class);
    when(resultItem.getId()).thenReturn("abcde");
    when(resultItem.getTitle()).thenReturn("Titel");
    when(resultItem.getSubtitle()).thenReturn("Untertitel");
    when(resultItem.getThumbnail()).thenReturn("/thumbnail.jpg");

    ArrayList<SearchResultItem> resultItems = new ArrayList<>();
    resultItems.add(resultItem);
    SearchResults results = mock(SearchResults.class);
    when(results.getResultItems()).thenReturn(resultItems);

    String url = ApiUrls.searchUrl("Titel", 0, 10, "relevance", "authkey");
    when(rest.getForObject(url, SearchResults.class)).thenReturn(results);

    DDBItem[] items = fetcher.searchForItems("Titel", 0, 10, "relevance");
    assertEquals(1, items.length);
    assertEquals("abcde", items[0].getId());
    assertEquals("Titel", items[0].getTitle());
    assertEquals("Untertitel", items[0].getSubtitle());
    assertEquals("https://www.deutsche-digitale-bibliothek.de/thumbnail.jpg",
        items[0].getImageUrl());
  }
  
  @Test
  public void fetch() throws ParserConfigurationException {
    String xpathIssued = "//dcterms:issued";
    String xpathInstitution = "//edm:dataProvider[1]";
    String xpathName = "//gndo:variantNameForThePerson";
    String xpathDOB  = "//gndo:dateOfBirth";
    String xpathDOD  = "//gndo:dateOfDeath";
    String xpathPOD  = "//gndo:placeOfDeath/rdf:Description/@rdf:about";
    String xpathLoc  = "//gndo:geographicAreaCode/@rdf:resource";
    String xpathFacetRole = "//ctx:facet[@name='affiliate_fct_role_normdata']/ctx:value";
    String xpathFacet = "//ctx:facet[@name='affiliate_fct_normdata']/ctx:value";
    when(xpathTemplate.evaluateAsString("//dcterms:issued", domSource)).thenReturn("1946");
    when(xpathTemplate.evaluateAsString(xpathInstitution, domSource)).thenReturn("Deutsche Digitale Bibliothek");

    when(xpathTemplate.evaluateAsString(xpathDOB, domSource)).thenReturn("1748-05-14");
    when(xpathTemplate.evaluateAsString(xpathDOD, domSource)).thenReturn("1832-06-16");
    when(xpathTemplate.evaluateAsString(xpathPOD, domSource)).thenReturn("http://d-nb.info/gnd/4062257-5");
    when(xpathTemplate.evaluateAsString(xpathLoc, domSource)).thenReturn("test#XA-DE-TF");
    when(xpathTemplate.evaluateAsString(xpathName, domSource)).thenReturn("Johann Wolfgang von Goethe");
    Calendar calB = new GregorianCalendar(1749, 05, 14);
    Calendar calD = new GregorianCalendar(1832,06,16);

    Node node = mock(Node.class);
    Node nodeChild = mock(Node.class);
    node.appendChild(nodeChild);
    when(nodeChild.getTextContent()).thenReturn("http://d-nb.info/gnd/118540238");
    when(node.getFirstChild()).thenReturn(nodeChild);
    List<Node> nodes = new ArrayList<>();
    nodes.add(node);
    when(xpathTemplate.evaluateAsNodeList(xpathFacet, domSource)).thenReturn(nodes);
    
    
    String url = "https://api.deutsche-digitale-bibliothek.de/items/itemId/aip?oauth_consumer_key=authkey";
    String url2 = "http://d-nb.info/gnd/118540238/about/rdf";
    String url3 = "http://d-nb.info/gnd/4062257-5/about/rdf";
    when(rest.getForObject(url, DOMSource.class)).thenReturn(domSource);
    
    when(rest.getForObject(url2, DOMSource.class)).thenReturn(domSource);

    when(rest.getForObject(url3, DOMSource.class)).thenReturn(domSource);
    
    DDBItem ddbItem = fetcher.fetchMetadata("itemId");
    
    assertEquals("Deutsche Digitale Bibliothek",ddbItem.getInstitution());
    assertEquals(1946,ddbItem.getPublishedYear().get(Calendar.YEAR));
    assertEquals("http://d-nb.info/gnd/118540238", ddbItem.getAuthors().get(0).getDnbId());
    Author author = ddbItem.getAuthors().get(0);
    assertEquals("Johann Wolfgang von Goethe",author.getName());
    assertEquals(calB, author.getDateOfBirth());
    assertEquals(calD, author.getDateOfDeath());
    assertEquals("de", author.getPlaceOfBirth());
  } 
}
