/*
 * Test for PDCController
 */
package de.ddb.pdc.web;

import de.ddb.pdc.Main;
import de.ddb.pdc.core.PublicDomainCalculator;
import de.ddb.pdc.core.AnsweredQuestion;
import de.ddb.pdc.core.PDCResult;
import de.ddb.pdc.metadata.DDBItem;
import de.ddb.pdc.metadata.MetaFetcher;
import de.ddb.pdc.storage.StorageService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assume.assumeNotNull;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@ActiveProfiles({"test"})
public class PDCControllerTest {

  @Test
  public void determinePublicDomain() throws Exception{
    MetaFetcher fetcher = mock(MetaFetcher.class);
    DDBItem item = new DDBItem("123");
    when(fetcher.fetchMetadata("123")).thenReturn(item);

    PublicDomainCalculator calculator = mock(PublicDomainCalculator.class);
    List<AnsweredQuestion> trace = new ArrayList<>();
    PDCResult pdcResult = new PDCResult(true, trace, item);
    when(calculator.calculate(null, item)).thenReturn(pdcResult);

    PDCController controller = new PDCController(fetcher, calculator);
    Assert.assertSame(pdcResult, controller.determinePublicDomain("123"));
  }

  @Test
  public void determinePublicDomainWithDB_notYetStored() throws Exception {
    MetaFetcher fetcher = mock(MetaFetcher.class);
    DDBItem item = new DDBItem("123");
    when(fetcher.fetchMetadata("123")).thenReturn(item);

    PublicDomainCalculator calculator = mock(PublicDomainCalculator.class);
    List<AnsweredQuestion> trace = new ArrayList<>();
    PDCResult pdcResult = new PDCResult(true, trace, item);
    when(calculator.calculate(null, item)).thenReturn(pdcResult);

    StorageService storage = mock(StorageService.class);
    PDCController controller = new PDCController(fetcher, calculator);
    controller.setStorageService(storage);

    Assert.assertSame(pdcResult, controller.determinePublicDomain("123"));
    verify(storage).store(pdcResult);
  }

  @Test
  public void determinePublicDomainWithDB_alreadyStored() throws Exception {
    MetaFetcher fetcher = mock(MetaFetcher.class);
    DDBItem item = new DDBItem("123");
    when(fetcher.fetchMetadata("123")).thenReturn(item);

    PublicDomainCalculator calculator = mock(PublicDomainCalculator.class);
    List<AnsweredQuestion> trace = new ArrayList<>();
    PDCResult pdcResult = new PDCResult(true, trace, item);
    when(calculator.calculate(null, item)).thenReturn(pdcResult);

    StorageService storage = mock(StorageService.class);
    when(storage.fetch("123")).thenReturn(pdcResult);
    PDCController controller = new PDCController(fetcher, calculator);
    controller.setStorageService(storage);

    Assert.assertSame(pdcResult, controller.determinePublicDomain("123"));
    verifyZeroInteractions(fetcher);
    verifyZeroInteractions(calculator);
  }
}
