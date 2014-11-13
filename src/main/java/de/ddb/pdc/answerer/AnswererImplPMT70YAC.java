package de.ddb.pdc.answerer;

import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the PUBLISHED_MORE_THAN_70_YEAR_AFTER_CREATION question.
 */
public class AnswererImplPMT70YAC implements Answerer {

  @Override
  public Answer getAnswer(DDBItem metaData) {
    if (metaData.yearPublished - metaData.yearOfCreation > 70) {
      return Answer.YES;
    } else {
      return Answer.NO;
    }
  }
  
}
