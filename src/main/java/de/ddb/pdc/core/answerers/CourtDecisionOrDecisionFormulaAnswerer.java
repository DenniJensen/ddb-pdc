package de.ddb.pdc.core.answerers;

import de.ddb.pdc.core.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the COURT_DECISION_OR_DECISION_FORMULA question.
 *
 * FIXME hard coded answer
 */
class CourtDecisionOrDecisionFormulaAnswerer implements Answerer {

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
  public String getNoteForLastQuestion() {
    return "The answer is always assumed to be no. A court decision or decision"
        + " formula would fall into public domain without further limitations.";
  }

}
