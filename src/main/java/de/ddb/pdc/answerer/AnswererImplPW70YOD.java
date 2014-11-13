package de.ddb.pdc.answerer;

import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the PUBLISHED_WITHIN_70_YEARS_OF_DEATH question.
 */
public class AnswererImplPW70YOD implements Answerer {

  @Override
  public Answer getAnswer(DDBItem metaData) {
    if (metaData.yearPublished < metaData.authorYearOfDeath + 70) {
      return Answer.YES;
    } else {
      return Answer.NO;
    }
  }
  
}
