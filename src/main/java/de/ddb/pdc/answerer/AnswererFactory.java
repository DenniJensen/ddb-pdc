package de.ddb.pdc.answerer;

import org.springframework.stereotype.Service;

import de.ddb.pdc.answerer.answerers.ActOfParliamentAnswerer;
import de.ddb.pdc.answerer.answerers.AnnouncementByAuthorityAnswerer;
import de.ddb.pdc.answerer.answerers.AnonymousAauthorAnswerer;
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

/**
 * The answerer factory creates answerer instances based on the question.
 */
@Service
public class AnswererFactory {

  /**
   * Creates and returns a specific implementation of {@link Answerer} depending
   * on the type of question provided
   *
   * @param question The question to create an answerer for.
   * @return required Answerer implementation.
   */
  @SuppressWarnings("static-method")
  public Answerer getAnswererForQuestion(Question question) {
    switch (question) {
      case AUTHOR_FROM_TRIPTIS:
        return new AuthorFromTriptisAnswerer();
      case COUNTRY_OF_ORIGIN_TRIPTIS:
        return new CountryOfOriginTriptisAnswerer();
      case CREATED_MORE_THAN_70_YEARS_AGO:
        return new CreatedMoreThan70YearsAgoAnswerer();
      case PUBLISHED_MORE_THAN_25_YEARS_AGO:
        return new PublishedMoreThan25YearsAgoAnswerer();
      case PUBLISHED_MORE_THAN_70_YEAR_AFTER_CREATION:
        return new PublishedMoreThan70YearsAfterCreationAnswerer();
      case WORK_PUBLISHED_OR_COMMUNICATED:
        return new WorkPublishedOrCommunicatedAnswerer();
      case COUNTRY_OF_ORIGIN_EEA:
        return new CountryOfOriginEuropeanEconomicAreaAnswerer();
      case AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA:
        return new AuthorFromEuropeanEconomicAreaAnswerer();
      case AUTHOR_ANONYMOUS:
        return new AnonymousAauthorAnswerer();
      case AUTHOR_DIED_MORE_THAN_70_YEARS_AGO:
        return new AuthorDiedMoreThan70YearsAgoAnswerer();
      case PUBLISHED_WITHIN_70_YEARS_OF_DEATH:
        return new PublishedWithin70YearsOfDeathAnswerer();
      case AUTHOR_NATURAL_PERSON:
        return new AuthorNaturalPersonAnswerer();
      case ANNOUNCEMENT_BY_AUTHORITY:
        return new AnnouncementByAuthorityAnswerer();
      case GOVERNMENT_DIRECTIVE:
        return new GovernmentDecisionAnswerer();
      case ACT_OF_PARLIAMENT:
        return new ActOfParliamentAnswerer();
      case COURT_DECISION_OR_DECISION_FORMULA:
        return new CourtDecisionOrDecisionFormulaAnswerer();
      case OFFICIAL_WORK_INTENDED_TO_BE_GENERALLY_RECEIVED:
        return new OfficialWorkToBeGenerallyReceivedAnswerer();
      default:
        throw new IllegalArgumentException("No answerer available for "
            + question);
    }
  }
}
