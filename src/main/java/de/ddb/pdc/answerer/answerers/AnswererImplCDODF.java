package de.ddb.pdc.answerer;

import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the COURT_DECISION_OR_DECISION_FORMULA question.
 * 
 * FIXME hard coded answer
 */
public class AnswererImplCDODF implements Answerer {

  @Override
  public Answer getAnswer(DDBItem metaData) {
    return Answer.NO;
  }
  
}
