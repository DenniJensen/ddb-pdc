package de.ddb.pdc.answerer;

import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the ANNOUNCEMENT_BY_AUTHORITY question.
 * 
 * FIXME hard coded answer
 */
public class AnswererImplABA implements Answerer {

  @Override
  public Answer getAnswer(DDBItem metaData) {
    return Answer.NO;
  }
  
}
