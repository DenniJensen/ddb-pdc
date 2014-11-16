package de.ddb.pdc.answerer.answerers;

import java.util.Calendar;

import de.ddb.pdc.answerer.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the PUBLISHED_MORE_THAN_70_YEAR_AFTER_CREATION question.
 *
 * FIXME Assumption: published year is equal to created year
 */
public class PublishedMoreThan70YearsAfterCreationAnswerer implements Answerer {

  /**
   * {@inheritDoc}
   */
  @Override
  public Answer getAnswer(DDBItem metaData) {
    if (metaData.getPublishedYear().get(Calendar.YEAR) - metaData.getPublishedYear().get(Calendar.YEAR) > 70) {
      return Answer.YES;
    } else {
      return Answer.NO;
    }
  }

}
