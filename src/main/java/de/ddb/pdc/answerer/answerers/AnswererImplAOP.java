package de.ddb.pdc.answerer;

import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the ACT_OF_PARLIAMENT question.
 * 
 * FIXME hard coded answer
 */
public class AnswererImplAOP implements Answerer {

  @Override
  public Answer getAnswer(DDBItem metaData) {
    return Answer.NO;
  }
  
}
