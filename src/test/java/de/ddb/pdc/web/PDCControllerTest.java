/*
 * Test for PDCController
 */
package de.ddb.pdc.web;

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
import org.mockito.Mockito;

public class PDCControllerTest {

  @Test
  public void determinePublicDomainTest() throws Exception{

    MetaFetcher mfetcher = Mockito.mock(MetaFetcher.class);
    DDBItem ddbItemfromMetaFetcher = new DDBItem("123");
    Mockito.when(mfetcher.fetchMetadata("123")).thenReturn(ddbItemfromMetaFetcher);

    PublicDomainCalculator ansService = Mockito.mock(PublicDomainCalculator.class);
    List<AnsweredQuestion> trace = new ArrayList<AnsweredQuestion>();
    PDCResult pdcResult = new PDCResult(true,trace);
    // country is null
    Mockito.when(ansService.calculate(null, mfetcher.fetchMetadata("123"))).thenReturn(pdcResult);

    StorageService storageService = Mockito.mock(StorageService.class);

    PDCController pdcController = new PDCController(
        mfetcher, ansService, storageService
    );

    Assert.assertSame(pdcResult, pdcController.determinePublicDomain("123"));
  }

}
