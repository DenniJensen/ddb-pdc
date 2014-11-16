package de.ddb.pdc.answerer.answerers;

import de.ddb.pdc.answerer.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the PUBLISHED_MORE_THAN_70_YEAR_AFTER_CREATION question.
 */
public class PublishedMoreThan70YearsAfterCreationAnswerer implements Answerer {

  /**
   * {@inheritDoc}
   */
  @Override
  public Answer getAnswer(DDBItem metaData) {
    if (metaData.getYearPublished() - metaData.getYearOfCreation() > 70) {
      return Answer.YES;
    } else {
      return Answer.NO;
    }
  }

}
