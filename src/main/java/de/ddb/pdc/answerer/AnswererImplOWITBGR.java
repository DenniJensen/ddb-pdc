package de.ddb.pdc.answerer;

import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the OFFICIAL_WORK_INTENDED_TO_BE_GENERALLY_RECEIVED question.
 * 
 * TODO hard coded answer
 */
public class AnswererImplOWITBGR implements Answerer {

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
