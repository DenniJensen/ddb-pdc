package de.ddb.pdc.metadata;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

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
    ResultsOfJSON result = mock(ResultsOfJSON.class);
    when(result.getResults()).thenReturn(resultItems);

    String url = "https://api.deutsche-digitale-bibliothek.de/search?oauth_consumer_key=authkey&sort=RELEVANCE&rows=10&query=Titel";
    when(rest.getForObject(url, ResultsOfJSON.class)).thenReturn(result);

    DDBItem[] items = fetcher.searchForItems("Titel", 10);
    assertEquals(1, items.length);
    assertEquals("abcde", items[0].getId());
    assertEquals("Titel", items[0].getTitle());
    assertEquals("Untertitel", items[0].getSubtitle());
    assertEquals("https://www.deutsche-digitale-bibliothek.de/thumbnail.jpg",
        items[0].getImageUrl());
  }
}
