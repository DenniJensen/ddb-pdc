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
   * FIXME there is no nationality-field of the work at the moment
   * FIXME this is mis-using the author-nationality.
   */
  @Override
  public Answer getAnswer(DDBItem metaData) {
    // FIXME wrong nationality used here
    String country = metaData.getAuthors().get(0).getNationality();
    if (EEAMembers.isMember(country)) {
      return Answer.YES;
    } else {
      return Answer.NO;
    }
  }

}
