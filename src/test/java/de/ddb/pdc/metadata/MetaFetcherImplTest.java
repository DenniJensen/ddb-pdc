package de.ddb.pdc.metadata;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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

    String url = "https://api.deutsche-digitale-bibliothek.de/search?oauth_consumer_key=authkey&sort=RELEVANCE&rows=10&query=Titel";
    when(rest.getForObject(url, SearchResults.class)).thenReturn(results);

    DDBItem[] items = fetcher.searchForItems("Titel", 10);
    assertEquals(1, items.length);
    assertEquals("abcde", items[0].getId());
    assertEquals("Titel", items[0].getTitle());
    assertEquals("Untertitel", items[0].getSubtitle());
    assertEquals("https://www.deutsche-digitale-bibliothek.de/thumbnail.jpg",
        items[0].getImageUrl());
  }
  
  @Test
  public void fetch() {
    EntitiesResultItem eri = mock(EntitiesResultItem.class);
    when(eri.getName()).thenReturn("Johann Wolfgang von Goethe");
    when(eri.getPlaceOfBirth()).thenReturn("Frankfurt am Main");
    when(eri.getYearOfBirth()).thenReturn(1749);
    when(eri.getYearOfDeath()).thenReturn(1832);

    EntitiesResult er = mock(EntitiesResult.class);
    when(er.getResultItem()).thenReturn(eri);
    
    RDFItem rdf = mock(RDFItem.class);
    String authorId = "http://d-nb.info/gnd/118540238";
    List<String> authorIds = new ArrayList<>();
    authorIds.add(authorId);
    when(rdf.getAuthorIds()).thenReturn(authorIds);
    when(rdf.getInstitution()).thenReturn("Deutsche Digitale Bibliothek");
    when(rdf.getPublishYear()).thenReturn(1946);
    
    EDMItem edm = mock(EDMItem.class);
    when(edm.getRdf()).thenReturn(rdf);
    
    ItemAipResult result = mock(ItemAipResult.class);
    when(result.getRDFItem()).thenReturn(rdf);
    
    
    String url = "https://api.deutsche-digitale-bibliothek.de/items/itemId/aip?oauth_consumer_key=authkey";
    String url2 = "https://api.deutsche-digitale-bibliothek.de/entities?query=id:\"http://d-nb.info/gnd/118540238\"&oauth_consumer_key=authkey";
    
    when(rest.getForObject(url, ItemAipResult.class)).thenReturn(result);
    
    when(rest.getForObject(url2, EntitiesResult.class)).thenReturn(er);
    
    DDBItem ddbItem = fetcher.fetchMetadata("itemId");
    
    assertEquals("Deutsche Digitale Bibliothek",ddbItem.getInstitution());
    assertEquals(1946,ddbItem.getPublishedYear().get(Calendar.YEAR));
    assertEquals("http://d-nb.info/gnd/118540238", ddbItem.getAuthors().get(0).getDnbId());
    Author author = ddbItem.getAuthors().get(0);
    assertEquals("Johann Wolfgang von Goethe",author.getName());
    assertEquals(1749, author.getYearOfBirth().get(Calendar.YEAR));
    assertEquals(1832, author.getYearOfDeath().get(Calendar.YEAR));
    assertEquals("Frankfurt am Main", author.getPlaceOfBirth());
  } 
}
