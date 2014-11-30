package de.ddb.pdc.core.answerers;

import org.springframework.stereotype.Service;

import de.ddb.pdc.core.Answerer;
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
      case ACT_OF_PARLIAMENT:
        return new ActOfParliamentAnswerer();
      case ANNOUNCEMENT_BY_AUTHORITY:
        return new AnnouncementByAuthorityAnswerer();
      case AUTHOR_ANONYMOUS:
        return new AnonymousAuthorAnswerer();
      case AUTHOR_DIED_MORE_THAN_70_YEARS_AGO:
        return new AuthorDiedMoreThan70YearsAgoAnswerer();
      case AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA:
        return new AuthorFromEuropeanEconomicAreaAnswerer();
      case AUTHOR_FROM_TRIPTIS:
        return new AuthorFromTriptisAnswerer();
      case AUTHOR_NATURAL_PERSON:
        return new AuthorNaturalPersonAnswerer();
      case COUNTRY_OF_ORIGIN_EEA:
        return new CountryOfOriginEuropeanEconomicAreaAnswerer();
      case COUNTRY_OF_ORIGIN_TRIPTIS:
        return new CountryOfOriginTriptisAnswerer();
      case COURT_DECISION_OR_DECISION_FORMULA:
        return new CourtDecisionOrDecisionFormulaAnswerer();
      case CREATED_MORE_THAN_70_YEARS_AGO:
        return new CreatedMoreThan70YearsAgoAnswerer();
      case GOVERNMENT_DIRECTIVE:
        return new GovernmentDecisionAnswerer();
      case OFFICIAL_WORK_INTENDED_TO_BE_GENERALLY_RECEIVED:
        return new OfficialWorkToBeGenerallyReceivedAnswerer();
      case PUBLISHED_MORE_THAN_25_YEARS_AGO:
        return new PublishedMoreThan25YearsAgoAnswerer();
      case PUBLISHED_MORE_THAN_70_YEAR_AFTER_CREATION:
        return new PublishedMoreThan70YearsAfterCreationAnswerer();
      case PUBLISHED_WITHIN_70_YEARS_OF_DEATH:
        return new PublishedWithin70YearsOfDeathAnswerer();
      case WORK_PUBLISHED_OR_COMMUNICATED:
        return new WorkPublishedOrCommunicatedAnswerer();
      default:
        throw new IllegalArgumentException("No answerer available for "
            + question);
    }
  }
}
