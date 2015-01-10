package de.ddb.pdc.metadata;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import org.springframework.xml.xpath.Jaxp13XPathTemplate;
import org.springframework.xml.xpath.XPathOperations;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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
  public void fetch() throws Exception {
    final String itemId = "UGTZDTFHRNELDDLG2BGYKJMSVIB4XSML";
    final String authorId = "http://d-nb.info/gnd/118540238";
    final String placeId = "http://d-nb.info/gnd/4065105-8";

    String itemUrl = ApiUrls.itemAipUrl(itemId, "authkey");
    DOMSource itemXml = loadXml("/ddb_items_aip/" + itemId);
    when(rest.getForObject(itemUrl, DOMSource.class)).thenReturn(itemXml);

    String authorUrl = ApiUrls.dnbUrl(authorId);
    DOMSource authorXml = loadDnbXml(authorId);
    when(rest.getForObject(authorUrl, DOMSource.class)).thenReturn(authorXml);

    String placeUrl = ApiUrls.dnbUrl(placeId);
    DOMSource placeXml = loadDnbXml(placeId);
    when(rest.getForObject(placeUrl, DOMSource.class)).thenReturn(placeXml);

    DDBItem item = fetcher.fetchMetadata(itemId);
    assertEquals("Bayerische Staatsbibliothek", item.getInstitution());
    assertEquals(1849, item.getPublishedYear().get(Calendar.YEAR));
    assertEquals(1, item.getAuthors().size());

    Author author = item.getAuthors().get(0);
    assertEquals("http://d-nb.info/gnd/118540238", author.getDnbId());
    assertEquals("Goethe, Johann Wolfgang v.", author.getName());
    assertEquals(new GregorianCalendar(1749, 8, 28), author.getDateOfBirth());
    assertEquals(new GregorianCalendar(1832, 3, 22), author.getDateOfDeath());
    assertEquals("de", author.getNationality());
  }

  private DOMSource loadXml(String path) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(getClass().getResourceAsStream(path + ".xml"));
    return new DOMSource(doc.getDocumentElement());
  }

  private DOMSource loadDnbXml(String dnbId) throws Exception {
    String gndNumber = dnbId.split("/")[4];
    return loadXml("/dnb/" + gndNumber);
  }
}
