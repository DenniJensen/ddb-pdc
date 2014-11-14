
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import de.ddb.pdc.metadata.MetaFetcher;
import de.ddb.pdc.metadata.DDBItem;
import de.ddb.pdc.web.SearchController;

public class SearchControllerTest {

  @Test
  public void search() {
    MetaFetcher fetcher = mock(MetaFetcher.class);
    DDBItem[] result = new DDBItem[0];
    when(fetcher.searchForItems("foo", 10)).thenReturn(result);
    SearchController controller = new SearchController(fetcher);
    assertSame(result, controller.search("foo", 10));
  }
}
