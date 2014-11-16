package de.ddb.pdc.answerer;

import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the COUNTRY_OF_ORIGIN_TRIPTIS question.
 */
public class AnswererImplCOOT implements Answerer {

  /**
   * Answer whether the country that the item was created in is a member of
   * TRIPTIS.
   * 
   * TODO add TRIPTIS membership check
   * 
   * @param metaData
   * @return 
   */
  @Override
  public Answer getAnswer(DDBItem metaData) {
    return Answer.YES;
  }
  
}
