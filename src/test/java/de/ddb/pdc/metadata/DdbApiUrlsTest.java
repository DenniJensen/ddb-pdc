package de.ddb.pdc.metadata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DdbApiUrlsTest {

  @Test
  public void searchUrl() {
    assertEquals(
        "https://api.deutsche-digitale-bibliothek.de/search?query=foo&rows=123&sort=relevance&oauth_consumer_key=key",
        DdbApiUrls.searchUrl("foo", 123, "key"));
  }

  @Test
  public void itemAipUrl() {
    assertEquals(
        "https://api.deutsche-digitale-bibliothek.de/items/abcde/aip?oauth_consumer_key=key",
        DdbApiUrls.itemAipUrl("abcde", "key"));
  }

  @Test
  public void entityUrl() {
    assertEquals(
        "https://api.deutsche-digitale-bibliothek.de/entities?query=id:\"abcde\"&oauth_consumer_key=key",
        DdbApiUrls.entityUrl("abcde", "key"));
  }
}