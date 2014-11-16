package de.ddb.pdc.answerer;

import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the WORK_PUBLISHED_OR_COMMUNICATED question.
 * 
 * TODO hard coded answer.
 */
public class AnswererImplWPOC implements Answerer {

  /**
   * 
   * @param metaData
   * @return 
   */
  @Override
  public Answer getAnswer(DDBItem metaData) {
    return Answer.YES;
  }
  
}
