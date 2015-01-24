package de.ddb.pdc.core.answerers;


import de.ddb.pdc.core.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the COUNTRY_OF_ORIGIN_EEA question.
 */
class CountryOfOriginEuropeanEconomicAreaAnswerer implements Answerer {

  /**
   * Country of origin is always Germany as requested by the customer
   */
  @Override
  public Answer answerQuestionForItem(DDBItem metaData) {
    return Answer.ASSUMED_YES;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getNoteForLastQuestion() {
    return "Es wird immer davon ausgegangen, dass das Herkunftsland des"
        + " Werkes Deutschland ist.";
  }
}
