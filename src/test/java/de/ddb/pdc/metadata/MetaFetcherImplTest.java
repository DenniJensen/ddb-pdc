package de.ddb.pdc.metadata;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MetaFetcherImplTest {

  private MetaFetcherImpl fetcher;
  private RestTemplate rest;

  @Before
  public void setUp() {
    rest = mock(RestTemplate.class);
    fetcher = new MetaFetcherImpl(rest, "authkey");
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

    SearchItems searchItems = fetcher.searchForItems("Titel", 0, 10, "relevance");
    DDBItem[] items = searchItems.getDdbItems();
    assertEquals(1, items.length);
    assertEquals("abcde", items[0].getId());
    assertEquals("Titel", items[0].getTitle());
    assertEquals("Untertitel", items[0].getSubtitle());
    assertEquals("https://www.deutsche-digitale-bibliothek.de/thumbnail.jpg",
        items[0].getImageUrl());
  }
  
  @Test
  public void fetch() throws Exception {
    mockItemAipRequest("UGTZDTFHRNELDDLG2BGYKJMSVIB4XSML");
    mockDnbEntityRequest("http://d-nb.info/gnd/118540238");
    mockDnbEntityRequest("http://d-nb.info/gnd/4018118-2");

    DDBItem item = fetcher.fetchMetadata("UGTZDTFHRNELDDLG2BGYKJMSVIB4XSML");
    String url = "https://www.deutsche-digitale-bibliothek.de";

    assertEquals(
        "Goethe's Verklärung",
        item.getTitle());
    assertEquals(
      "Goethe, Johann Wolfgang von (1749-1832). - Leipzig : Dederich, ([1849])",
        item.getSubtitle());
    assertEquals(
        "https://www.deutsche-digitale-bibliothek.de/binary/UGTZDTFHRNELDDLG2BGYKJMSVIB4XSML/list/1.jpg",
        item.getImageUrl());
    assertEquals(
        "Bayerische Staatsbibliothek",
        item.getInstitution());
    assertEquals(
        1849,
        item.getPublishedYear().get(Calendar.YEAR));
    assertEquals(
        1,
        item.getAuthors().size());

    Author author = item.getAuthors().get(0);
    assertEquals("http://d-nb.info/gnd/118540238", author.getDnbId());
    assertEquals("Goethe, Johann Wolfgang von", author.getName());
    assertEquals(new GregorianCalendar(1749, 7, 28), author.getDateOfBirth());
    assertEquals(new GregorianCalendar(1832, 2, 22), author.getDateOfDeath());
    assertEquals("de", author.getNationality());
  }

  @Test
  public void fetchPublicDomain() throws Exception {
    mockItemAipRequest("NF42ZIDML4FWIBPEUEZDW6QDBSJIV7VR");
    DDBItem item = fetcher.fetchMetadata("NF42ZIDML4FWIBPEUEZDW6QDBSJIV7VR");
    assertTrue(item.hasCcLicense());
    assertEquals("zero", item.getCcLicense());
  }

  private void mockItemAipRequest(String itemId) throws Exception {
    String itemUrl = ApiUrls.itemAipUrl(itemId, "authkey");
    DOMSource itemXml = loadXml("/ddb_items_aip/" + itemId);
    when(rest.getForObject(itemUrl, DOMSource.class)).thenReturn(itemXml);
  }

  private void mockDnbEntityRequest(String dnbUri) throws Exception {
    String dnbRequestUrl = ApiUrls.dnbUrl(dnbUri);
    String dnbId = dnbUri.split("/")[4];
    DOMSource authorXml = loadXml("/dnb/" + dnbId);
    when(rest.getForObject(dnbRequestUrl, DOMSource.class))
        .thenReturn(authorXml);
  }

  private DOMSource loadXml(String path) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(getClass().getResourceAsStream(path + ".xml"));
    return new DOMSource(doc.getDocumentElement());
  }
}
