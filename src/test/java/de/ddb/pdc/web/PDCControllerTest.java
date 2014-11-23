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
    AnswererService ansService = Mockito.mock(AnswererService.class);
    DDBItem ddbItemfromMetaFetcher = new DDBItem("123");
    List<AnsweredQuestion> trace = new ArrayList<AnsweredQuestion>();
    PDCResult pdcResult = new PDCResult(true,trace);
    Mockito.when(mfetcher.fetchMetadata("123")).thenReturn(ddbItemfromMetaFetcher);
    Mockito.when(ansService.getResult("Germany", ddbItemfromMetaFetcher)).thenReturn(pdcResult);
    PDCController pdcController = new PDCController(mfetcher, ansService);
    Mockito.when(pdcController.calculate("123")).thenReturn(pdcResult);

    Assert.assertSame(pdcResult, pdcController.calculate("123"));
  }

}
