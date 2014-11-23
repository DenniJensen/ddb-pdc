/*
 * Test for PDCController
 */
package de.ddb.pdc.web;

import de.ddb.pdc.answerer.AnswererService;
import de.ddb.pdc.core.AnsweredQuestion;
import de.ddb.pdc.core.PDCResult;
import de.ddb.pdc.metadata.DDBItem;
import de.ddb.pdc.metadata.MetaFetcher;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class PDCControllerTest {

  @Test
  public void calculate() throws Exception{

    MetaFetcher mfetcher = Mockito.mock(MetaFetcher.class);
    DDBItem ddbItemfromMetaFetcher = new DDBItem("123");
    Mockito.when(mfetcher.fetchMetadata("123")).thenReturn(ddbItemfromMetaFetcher);

    AnswererService ansService = Mockito.mock(AnswererService.class);
    List<AnsweredQuestion> trace = new ArrayList<AnsweredQuestion>();
    PDCResult pdcResult = new PDCResult(true,trace);
    // country is null
    Mockito.when(ansService.getResult(null, mfetcher.fetchMetadata("123"))).thenReturn(pdcResult);

    PDCController pdcController = new PDCController(mfetcher, ansService);

    Assert.assertSame(pdcResult, pdcController.calculate("123"));
  }

}
