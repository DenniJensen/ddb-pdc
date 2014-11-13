package de.ddb.pdc.answerer;

import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the COUNTRY_OF_ORIGIN_EEA question.
 */
public class AnswererImplCOOE implements Answerer {

  /**
   * Answer whether the country that the item was created in is a member of the
   * EEA.
   * 
   */
  @Override
  public Answer getAnswer(DDBItem metaData) {
    String country = metaData.countryCreatedIn;
    if (EEAMembers.isMember(country)) {
      return Answer.YES;
    } else {
      return Answer.NO;
    }
  }
  
}
