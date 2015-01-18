package de.ddb.pdc.web;


import de.ddb.pdc.metadata.SearchItems;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import de.ddb.pdc.metadata.DDBItem;
import de.ddb.pdc.metadata.MetaFetcher;

public class SearchControllerTest {

  @Test
  public void search() {
    MetaFetcher fetcher = Mockito.mock(MetaFetcher.class);
    DDBItem[] result = new DDBItem[0];
    SearchItems searchItems = new SearchItems(0, result);
    Mockito.when(fetcher.searchForItems("foo", 0, 10, "relevance")).thenReturn(searchItems);
    SearchController controller = new SearchController(fetcher);
    Assert.assertSame(searchItems, controller.search("foo", 10, null));
  }
}
