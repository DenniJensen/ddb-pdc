package de.ddb.pdc.core.answerers;

import de.ddb.pdc.core.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the PUBLISHED_MORE_THAN_70_YEAR_AFTER_CREATION question.
 *
 * FIXME Assumption: published year is equal to created year
 * FIXME Therefore answer is hardcodede no
 */
class PublishedMoreThan70YearsAfterCreationAnswerer implements Answerer {

  /**
   * {@inheritDoc}
   */
  @Override
  public Answer answerQuestionForItem(DDBItem metaData) {
    return Answer.ASSUMED_NO;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getNoteForLastQuestion() {
    return "Es wird davon ausgegangen, dass das Erstellungs- und "
        + "Veröffentlichungsjahr identisch sind.";
  }

}
