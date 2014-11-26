package de.ddb.pdc.answerer.answerers;

import de.ddb.pdc.answerer.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the AUTHOR_FROM_TRIPTIS question.
 *
 */
class AuthorFromTriptisAnswerer implements Answerer {

  /**
   * Answers whether the country that the author originates from is a member of
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
    return "Hardcoded answer yes";
  }
}
