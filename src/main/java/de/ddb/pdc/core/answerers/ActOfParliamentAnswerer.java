package de.ddb.pdc.core.answerers;

import de.ddb.pdc.core.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the ACT_OF_PARLIAMENT question.
 *
 * FIXME hard coded answer
 */
class ActOfParliamentAnswerer implements Answerer {

  /**
   * {@inheritDoc}
   */
  @Override
  public Answer answerQuestionForItem(DDBItem metaData) {
    return Answer.ASSUMED_NO;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getAssumptionForLastAnswer() {
    return "Hardcoded answer. The answer will always be no.";
  }
}
