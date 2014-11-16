package de.ddb.pdc.answerer.answerers;

import de.ddb.pdc.answerer.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the PUBLISHED_WITHIN_70_YEARS_OF_DEATH question.
 */
public class AnswererImplPW70YOD implements Answerer {

  /**
   * {@inheritDoc}
   */
  @Override
  public Answer getAnswer(DDBItem metaData) {
    if (metaData.getYearPublished() < metaData.getAuthorYearOfDeath() + 70) {
      return Answer.YES;
    } else {
      return Answer.NO;
    }
  }

}
