package de.ddb.pdc.answerer;

import org.junit.Assert;
import org.junit.Test;

import de.ddb.pdc.answerer.answerers.AnonymousAauthorAnswerer;
import de.ddb.pdc.answerer.answerers.AnnouncementByAuthorityAnswerer;
import de.ddb.pdc.answerer.answerers.AuthorDiedMoreThan70YearsAgoAnswerer;
import de.ddb.pdc.answerer.answerers.AuthorFromEuropeanEconomicAreaAnswerer;
import de.ddb.pdc.answerer.answerers.AuthorFromTriptisAnswerer;
import de.ddb.pdc.answerer.answerers.AuthorNaturalPersonAnswerer;
import de.ddb.pdc.answerer.answerers.ActOfParliamentAnswerer;
import de.ddb.pdc.answerer.answerers.CourtDecisionOrDecisionFormulaAnswerer;
import de.ddb.pdc.answerer.answerers.CreatedMoreThan70YearsAgoAnswerer;
import de.ddb.pdc.answerer.answerers.CountryOfOriginEuropeanEconomicAreaAnswerer;
import de.ddb.pdc.answerer.answerers.CountryOfOriginTriptisAnswerer;
import de.ddb.pdc.answerer.answerers.GovernmentDecisionAnswerer;
import de.ddb.pdc.answerer.answerers.OfficialWorkToBeGenerallyReceivedAnswerer;
import de.ddb.pdc.answerer.answerers.PublishedMoreThan25YearsAgoAnswerer;
import de.ddb.pdc.answerer.answerers.PublishedMoreThan70YearsAfterCreationAnswerer;
import de.ddb.pdc.answerer.answerers.PublishedWithin70YearsOfDeathAnswerer;
import de.ddb.pdc.answerer.answerers.WorkPublishedOrCommunicatedAnswerer;
import de.ddb.pdc.core.Question;

@SuppressWarnings({"static-method", "javadoc"})
public class AnswererFactoryTest {

  @Test
  public void test() throws UnsupportedQuestionException {
    AnswererFactory factory = new AnswererFactory();

    Assert.assertTrue(
        factory.getAnswererForQuestion(Question.AUTHOR_ANONYMOUS)
        instanceof AnonymousAauthorAnswerer);

    Assert.assertTrue(
        factory.getAnswererForQuestion(Question.ANNOUNCEMENT_BY_AUTHORITY)
        instanceof AnnouncementByAuthorityAnswerer);

    Assert
        .assertTrue(
        factory
            .getAnswererForQuestion(Question.AUTHOR_DIED_MORE_THAN_70_YEARS_AGO)
        instanceof AuthorDiedMoreThan70YearsAgoAnswerer);

    Assert
        .assertTrue(
        factory
            .getAnswererForQuestion(Question.AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA)
        instanceof AuthorFromEuropeanEconomicAreaAnswerer);

    Assert
        .assertTrue(factory
            .getAnswererForQuestion(Question.AUTHOR_FROM_TRIPTIS)
        instanceof AuthorFromTriptisAnswerer);

    Assert
        .assertTrue(factory
            .getAnswererForQuestion(Question.AUTHOR_NATURAL_PERSON)
        instanceof AuthorNaturalPersonAnswerer);

    Assert
        .assertTrue(factory.getAnswererForQuestion(Question.ACT_OF_PARLIAMENT)
        instanceof ActOfParliamentAnswerer);

    Assert
        .assertTrue(factory
            .getAnswererForQuestion(Question.COURT_DECISION_OR_DECISION_FORMULA)
        instanceof CourtDecisionOrDecisionFormulaAnswerer);

    Assert
        .assertTrue(factory
            .getAnswererForQuestion(Question.CREATED_MORE_THAN_70_YEARS_AGO)
        instanceof CreatedMoreThan70YearsAgoAnswerer);

    Assert
        .assertTrue(factory
            .getAnswererForQuestion(Question.COUNTRY_OF_ORIGIN_EEA)
        instanceof CountryOfOriginEuropeanEconomicAreaAnswerer);

    Assert
        .assertTrue(factory
            .getAnswererForQuestion(Question.COUNTRY_OF_ORIGIN_TRIPTIS)
        instanceof CountryOfOriginTriptisAnswerer);

    Assert
        .assertTrue(factory
            .getAnswererForQuestion(Question.GOVERNMENT_DIRECTIVE)
        instanceof GovernmentDecisionAnswerer);

    Assert
        .assertTrue(factory
            .getAnswererForQuestion(
            Question.OFFICIAL_WORK_INTENDED_TO_BE_GENERALLY_RECEIVED)
        instanceof OfficialWorkToBeGenerallyReceivedAnswerer);

    Assert
        .assertTrue(factory
            .getAnswererForQuestion(Question.PUBLISHED_MORE_THAN_25_YEARS_AGO)
        instanceof PublishedMoreThan25YearsAgoAnswerer);

    Assert
        .assertTrue(factory
            .getAnswererForQuestion(
            Question.PUBLISHED_MORE_THAN_70_YEAR_AFTER_CREATION)
        instanceof PublishedMoreThan70YearsAfterCreationAnswerer);

    Assert
        .assertTrue(factory
            .getAnswererForQuestion(Question.PUBLISHED_WITHIN_70_YEARS_OF_DEATH)
        instanceof PublishedWithin70YearsOfDeathAnswerer);

    Assert
        .assertTrue(factory
            .getAnswererForQuestion(Question.WORK_PUBLISHED_OR_COMMUNICATED)
        instanceof WorkPublishedOrCommunicatedAnswerer);
  }
}
