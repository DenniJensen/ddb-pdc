package de.ddb.pdc.core.answerers;

import de.ddb.pdc.core.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the COUNTRY_OF_ORIGIN_TRIPTIS question.
 */
class CountryOfOriginTriptisAnswerer implements Answerer {

  /**
   * Answer whether the country that the item was created in is a member of
   * TRIPTIS.
   *
   * TODO add TRIPTIS membership check
   */
  @Override
  public Answer answerQuestionForItem(DDBItem metaData) {
    return Answer.ASSUMED_YES;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getAssumptionForLastAnswer() {
    return "hardcoded answer yes";
  }
}
