package de.ddb.pdc.answerer;

import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the GOVERNMENT_DIRECTIVE question.
 * 
 * TODO hard coded answer
 */
public class AnswererImplGD implements Answerer {

  /**
   * 
   * @param metaData
   * @return 
   */
  @Override
  public Answer getAnswer(DDBItem metaData) {
    return Answer.NO;
  }
  
}
