package de.ddb.pdc.metadata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DdbApiUrlsTest {

  private static final String API_URL_PREFIX =
      "https://api.deutsche-digitale-bibliothek.de";

  @Test
  public void searchUrl() {
    assertEquals(API_URL_PREFIX +
        "/search?query=foo&rows=123&sort=relevance&oauth_consumer_key=key",
        DdbApiUrls.searchUrl("foo", 123, "key"));
  }

  @Test
  public void itemAipUrl() {
    assertEquals(API_URL_PREFIX +
        "/items/abcde/aip?oauth_consumer_key=key",
        DdbApiUrls.itemAipUrl("abcde", "key"));
  }

  @Test
  public void entityUrl() {
    assertEquals(API_URL_PREFIX +
        "/entities?query=id:\"abcde\"&oauth_consumer_key=key",
        DdbApiUrls.entityUrl("abcde", "key"));
  }
}