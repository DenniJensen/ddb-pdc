package de.ddb.pdc.answerer.answerers;

import de.ddb.pdc.answerer.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the PUBLISHED_MORE_THAN_70_YEAR_AFTER_CREATION question.
 *
 * FIXME Assumption: published year is equal to created year
 * FIXME Therefore answer is hardcodede no
 */
public class PublishedMoreThan70YearsAfterCreationAnswerer implements Answerer {

  /**
   * {@inheritDoc}
   */
  @Override
  public Answer getAnswer(DDBItem metaData) {
    return Answer.NO;
  }

}
