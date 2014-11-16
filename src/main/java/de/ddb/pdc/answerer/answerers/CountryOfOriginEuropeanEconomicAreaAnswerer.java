package de.ddb.pdc.answerer.answerers;

import de.ddb.pdc.answerer.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the COUNTRY_OF_ORIGIN_EEA question.
 */
public class CountryOfOriginEuropeanEconomicAreaAnswerer implements Answerer {

  /**
   * Answer whether the country that the item was created in is a member of the
   * EEA.
   *
   */
  @Override
  public Answer getAnswer(DDBItem metaData) {
    String country = metaData.get;
    if (EEAMembers.isMember(country)) {
      return Answer.YES;
    } else {
      return Answer.NO;
    }
  }

}
