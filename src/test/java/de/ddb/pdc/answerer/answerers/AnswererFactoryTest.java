package de.ddb.pdc.answerer.answerers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.ddb.pdc.answerer.answerers.ActOfParliamentAnswerer;
import de.ddb.pdc.answerer.answerers.AnnouncementByAuthorityAnswerer;
import de.ddb.pdc.answerer.answerers.AnonymousAauthorAnswerer;
import de.ddb.pdc.answerer.answerers.AnswererFactory;
import de.ddb.pdc.answerer.answerers.AuthorDiedMoreThan70YearsAgoAnswerer;
import de.ddb.pdc.answerer.answerers.AuthorFromEuropeanEconomicAreaAnswerer;
import de.ddb.pdc.answerer.answerers.AuthorFromTriptisAnswerer;
import de.ddb.pdc.answerer.answerers.AuthorNaturalPersonAnswerer;
import de.ddb.pdc.answerer.answerers.CountryOfOriginEuropeanEconomicAreaAnswerer;
import de.ddb.pdc.answerer.answerers.CountryOfOriginTriptisAnswerer;
import de.ddb.pdc.answerer.answerers.CourtDecisionOrDecisionFormulaAnswerer;
import de.ddb.pdc.answerer.answerers.CreatedMoreThan70YearsAgoAnswerer;
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
  public void test() {
    AnswererFactory factory = new AnswererFactory();

    assertTrue(
        factory.getAnswererForQuestion(Question.AUTHOR_ANONYMOUS)
        instanceof AnonymousAauthorAnswerer);

    assertTrue(
        factory.getAnswererForQuestion(Question.ANNOUNCEMENT_BY_AUTHORITY)
        instanceof AnnouncementByAuthorityAnswerer);

    assertTrue(
        factory
            .getAnswererForQuestion(Question.AUTHOR_DIED_MORE_THAN_70_YEARS_AGO)
        instanceof AuthorDiedMoreThan70YearsAgoAnswerer);

    assertTrue(
        factory
            .getAnswererForQuestion(Question.AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA)
        instanceof AuthorFromEuropeanEconomicAreaAnswerer);

    assertTrue(factory
            .getAnswererForQuestion(Question.AUTHOR_FROM_TRIPTIS)
        instanceof AuthorFromTriptisAnswerer);

    assertTrue(factory
            .getAnswererForQuestion(Question.AUTHOR_NATURAL_PERSON)
        instanceof AuthorNaturalPersonAnswerer);

    assertTrue(factory.getAnswererForQuestion(Question.ACT_OF_PARLIAMENT)
        instanceof ActOfParliamentAnswerer);

    assertTrue(factory
            .getAnswererForQuestion(Question.COURT_DECISION_OR_DECISION_FORMULA)
        instanceof CourtDecisionOrDecisionFormulaAnswerer);

    assertTrue(factory
            .getAnswererForQuestion(Question.CREATED_MORE_THAN_70_YEARS_AGO)
        instanceof CreatedMoreThan70YearsAgoAnswerer);

    assertTrue(factory
            .getAnswererForQuestion(Question.COUNTRY_OF_ORIGIN_EEA)
        instanceof CountryOfOriginEuropeanEconomicAreaAnswerer);

    assertTrue(factory
            .getAnswererForQuestion(Question.COUNTRY_OF_ORIGIN_TRIPTIS)
        instanceof CountryOfOriginTriptisAnswerer);

    assertTrue(factory
            .getAnswererForQuestion(Question.GOVERNMENT_DIRECTIVE)
        instanceof GovernmentDecisionAnswerer);

    assertTrue(factory
            .getAnswererForQuestion(
            Question.OFFICIAL_WORK_INTENDED_TO_BE_GENERALLY_RECEIVED)
        instanceof OfficialWorkToBeGenerallyReceivedAnswerer);

    assertTrue(factory
            .getAnswererForQuestion(Question.PUBLISHED_MORE_THAN_25_YEARS_AGO)
        instanceof PublishedMoreThan25YearsAgoAnswerer);

    assertTrue(factory
            .getAnswererForQuestion(
            Question.PUBLISHED_MORE_THAN_70_YEAR_AFTER_CREATION)
        instanceof PublishedMoreThan70YearsAfterCreationAnswerer);

    assertTrue(factory
            .getAnswererForQuestion(Question.PUBLISHED_WITHIN_70_YEARS_OF_DEATH)
        instanceof PublishedWithin70YearsOfDeathAnswerer);

    assertTrue(factory
            .getAnswererForQuestion(Question.WORK_PUBLISHED_OR_COMMUNICATED)
        instanceof WorkPublishedOrCommunicatedAnswerer);
  }
}
