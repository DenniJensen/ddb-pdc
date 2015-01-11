/*
 * Test for PDCController
 */
package de.ddb.pdc.web;

import de.ddb.pdc.core.Answer;
import de.ddb.pdc.core.PublicDomainCalculator;
import de.ddb.pdc.core.AnsweredQuestion;
import de.ddb.pdc.core.PDCResult;
import de.ddb.pdc.core.Question;
import de.ddb.pdc.metadata.DDBItem;
import de.ddb.pdc.metadata.MetaFetcher;
import de.ddb.pdc.storage.StorageService;
import de.ddb.pdc.storage.TestConfiguration;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestConfiguration.class,
    loader = AnnotationConfigContextLoader.class)
public class PDCControllerTest {

  @Autowired
  private StorageService storageService;

  @Test
  public void determinePublicDomainWhenItemNotInDB() throws Exception{

    MetaFetcher mfetcher = Mockito.mock(MetaFetcher.class);
    DDBItem ddbItemfromMetaFetcher = new DDBItem("123");
    Mockito.when(mfetcher.fetchMetadata("123")).
        thenReturn(ddbItemfromMetaFetcher);

    PublicDomainCalculator ansService = Mockito.
        mock(PublicDomainCalculator.class);
    List<AnsweredQuestion> trace = new ArrayList<AnsweredQuestion>();
    PDCResult pdcResult = new PDCResult(true, trace, ddbItemfromMetaFetcher);
    // country is null
    Mockito.when(ansService.calculate(null, mfetcher.fetchMetadata("123"))).
        thenReturn(pdcResult);

    StorageService storageServiceMock = Mockito.mock(StorageService.class);
    String enableStorageProperty = "true";

    PDCController pdcController = new PDCController(
        mfetcher, ansService, storageServiceMock, enableStorageProperty
    );

    Assert.assertSame(pdcResult, pdcController.determinePublicDomain("123"));
  }

  @Test
  public void determinePublicDomainWhenItemIsInDB() throws Exception{

    final String itemID = "8963254";
    final String category = "Movies";
    final String institution = "Insti";
    final boolean publicDomain = false;
    final List<AnsweredQuestion> trace = new ArrayList<>();
    AnsweredQuestion answeredQuestionA = new AnsweredQuestion(
        Question.COUNTRY_OF_ORIGIN_EEA, Answer.YES, null
    );
    AnsweredQuestion answeredQuestionB = new AnsweredQuestion(
        Question.AUTHOR_DIED_MORE_THAN_70_YEARS_AGO, Answer.NO, null
    );
    trace.add(answeredQuestionA);
    trace.add(answeredQuestionB);

    DDBItem metadata = new DDBItem(itemID);
    metadata.setCategory(category);
    metadata.setInstitution(institution);

    PDCResult expectedPdcResult = new PDCResult(publicDomain, trace, metadata);
    storageService.store(expectedPdcResult);

    MetaFetcher mfetcher = Mockito.mock(MetaFetcher.class);
    PublicDomainCalculator ansService = Mockito.
        mock(PublicDomainCalculator.class);
    String enableStorageProperty = "true";

    PDCController pdcController = new PDCController(
        mfetcher, ansService, storageService, enableStorageProperty
    );

    PDCResult realPdcResult = pdcController.determinePublicDomain(itemID);

    Assert.assertTrue(compareTwoPDCResults(expectedPdcResult, realPdcResult));

    storageService.deleteAll();

  }

  /**
   * Compares two Objects from type PDCResult and return true if these have
   * the same values.
   *
   * @param pdc1
   * @param pdc2
   *
   * @return true if Objects are equal
   */
  private boolean compareTwoPDCResults(PDCResult pdc1, PDCResult pdc2){
    if (pdc1.isPublicDomain() != pdc2.isPublicDomain()){
      return false;
    }
    if (pdc1.getTrace().size() == pdc2.getTrace().size()){
      // both traces must have the same order
      for(int i = 0; i < pdc1.getTrace().size(); i++){
        if (!compareTwoAnsweredQuestions(pdc1.getTrace().get(i),
            pdc2.getTrace().get(i))){
          return false;
        }
      }
    } else {
      return false;
    }
    return true;
  }

  /**
   * Compares two Objects from type AnsweredQuestion and return true if these
   * have the same values.
   *
   * @param ansQ1
   * @param ansQ2
   *
   * @return true if Objects are equal
   */
  private boolean compareTwoAnsweredQuestions(AnsweredQuestion ansQ1,
          AnsweredQuestion ansQ2){
    if (!(ansQ1.getQuestion().equals(ansQ2.getQuestion())) ||
        !(ansQ1.getAnswer().equals(ansQ2.getAnswer()))){
      return false;
    }
    if ((ansQ1.getNote() == null && ansQ2.getNote() != null) ||
        (ansQ2.getNote() == null && ansQ1.getNote() != null)){
      return false;
    }
    else {
      if ((ansQ1.getNote() != null && ansQ2.getNote() != null) &&
          (!ansQ1.getNote().equals(ansQ2.getNote()))){
        return false;
      }
    }
    return true;
  }

}
