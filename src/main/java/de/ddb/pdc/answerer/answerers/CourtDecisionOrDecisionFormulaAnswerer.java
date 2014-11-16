package de.ddb.pdc.answerer.answerers;

import de.ddb.pdc.answerer.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the COURT_DECISION_OR_DECISION_FORMULA question.
 *
 * FIXME hard coded answer
 */
public class CourtDecisionOrDecisionFormulaAnswerer implements Answerer {

  /**
   * {@inheritDoc}
   */
  @Override
  public Answer getAnswer(DDBItem metaData) {
    return Answer.NO;
  }

}
