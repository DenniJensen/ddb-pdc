package de.ddb.pdc.answerer;

import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA question.
 */
public class AnswererImplAFEEA implements Answerer {

  /**
   * Answer whether the author's country is a member of the EEA.
   */
  @Override
  public Answer getAnswer(DDBItem metaData) {
    String country = metaData.getAuthorCountry();
    if (EEAMembers.isMember(country)) {
      return Answer.YES;
    } else {
      return Answer.NO;
    }
  }

}
