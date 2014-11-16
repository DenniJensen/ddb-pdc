package de.ddb.pdc.answerer;

import org.junit.Assert;
import org.junit.Test;

import de.ddb.pdc.answerer.answerers.AnswererImplAA;
import de.ddb.pdc.answerer.answerers.AnswererImplABA;
import de.ddb.pdc.answerer.answerers.AnswererImplADMT70YA;
import de.ddb.pdc.answerer.answerers.AnswererImplAFEEA;
import de.ddb.pdc.answerer.answerers.AnswererImplAFT;
import de.ddb.pdc.answerer.answerers.AnswererImplANP;
import de.ddb.pdc.answerer.answerers.AnswererImplAOP;
import de.ddb.pdc.answerer.answerers.AnswererImplCDODF;
import de.ddb.pdc.answerer.answerers.AnswererImplCMT70YA;
import de.ddb.pdc.answerer.answerers.AnswererImplCOOE;
import de.ddb.pdc.answerer.answerers.AnswererImplCOOT;
import de.ddb.pdc.answerer.answerers.AnswererImplGD;
import de.ddb.pdc.answerer.answerers.AnswererImplOWITBGR;
import de.ddb.pdc.answerer.answerers.AnswererImplPMT25YA;
import de.ddb.pdc.answerer.answerers.AnswererImplPMT70YAC;
import de.ddb.pdc.answerer.answerers.AnswererImplPW70YOD;
import de.ddb.pdc.answerer.answerers.AnswererImplWPOC;
import de.ddb.pdc.core.Question;

@SuppressWarnings({"static-method", "javadoc"})
public class AnswererFactoryTest {

  @Test
  public void test() throws UnsupportedQuestionException {
    AnswererFactory factory = new AnswererFactory();

    Assert.assertTrue(
        factory.getAnswererForQuestion(Question.AUTHOR_ANONYMOUS)
        instanceof AnswererImplAA);

    Assert.assertTrue(
        factory.getAnswererForQuestion(Question.ANNOUNCEMENT_BY_AUTHORITY)
        instanceof AnswererImplABA);

    Assert
        .assertTrue(
        factory
            .getAnswererForQuestion(Question.AUTHOR_DIED_MORE_THAN_70_YEARS_AGO)
        instanceof AnswererImplADMT70YA);

    Assert
        .assertTrue(
        factory
            .getAnswererForQuestion(Question.AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA)
        instanceof AnswererImplAFEEA);

    Assert
        .assertTrue(factory
            .getAnswererForQuestion(Question.AUTHOR_FROM_TRIPTIS)
        instanceof AnswererImplAFT);

    Assert
        .assertTrue(factory
            .getAnswererForQuestion(Question.AUTHOR_NATURAL_PERSON)
        instanceof AnswererImplANP);

    Assert
        .assertTrue(factory.getAnswererForQuestion(Question.ACT_OF_PARLIAMENT)
        instanceof AnswererImplAOP);

    Assert
        .assertTrue(factory
            .getAnswererForQuestion(Question.COURT_DECISION_OR_DECISION_FORMULA)
        instanceof AnswererImplCDODF);

    Assert
        .assertTrue(factory
            .getAnswererForQuestion(Question.CREATED_MORE_THAN_70_YEARS_AGO)
        instanceof AnswererImplCMT70YA);

    Assert
        .assertTrue(factory
            .getAnswererForQuestion(Question.COUNTRY_OF_ORIGIN_EEA)
        instanceof AnswererImplCOOE);

    Assert
        .assertTrue(factory
            .getAnswererForQuestion(Question.COUNTRY_OF_ORIGIN_TRIPTIS)
        instanceof AnswererImplCOOT);

    Assert
        .assertTrue(factory
            .getAnswererForQuestion(Question.GOVERNMENT_DIRECTIVE)
        instanceof AnswererImplGD);

    Assert
        .assertTrue(factory
            .getAnswererForQuestion(
            Question.OFFICIAL_WORK_INTENDED_TO_BE_GENERALLY_RECEIVED)
        instanceof AnswererImplOWITBGR);

    Assert
        .assertTrue(factory
            .getAnswererForQuestion(Question.PUBLISHED_MORE_THAN_25_YEARS_AGO)
        instanceof AnswererImplPMT25YA);

    Assert
        .assertTrue(factory
            .getAnswererForQuestion(
            Question.PUBLISHED_MORE_THAN_70_YEAR_AFTER_CREATION)
        instanceof AnswererImplPMT70YAC);

    Assert
        .assertTrue(factory
            .getAnswererForQuestion(Question.PUBLISHED_WITHIN_70_YEARS_OF_DEATH)
        instanceof AnswererImplPW70YOD);

    Assert
        .assertTrue(factory
            .getAnswererForQuestion(Question.WORK_PUBLISHED_OR_COMMUNICATED)
        instanceof AnswererImplWPOC);
  }
}
