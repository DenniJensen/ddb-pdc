package de.ddb.pdc.core.answerers;

import java.util.Calendar;

import de.ddb.pdc.core.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the CREATED_MORE_THAN_70_YEARS_AGO question.
 *
 * FIXME Assumption: published year is equal to created year
 */
class CreatedMoreThan70YearsAgoAnswerer implements Answerer {

  /**
   * {@inheritDoc}
   */
  @Override
  public Answer answerQuestionForItem(DDBItem metaData) {
    Calendar publishedYear = metaData.getPublishedYear();
    if (publishedYear == null || !publishedYear.isSet(Calendar.YEAR)) {
      return Answer.UNKNOWN;
    }
    Calendar calendar = Calendar.getInstance();
    int currentYear = calendar.get(Calendar.YEAR);
    if (currentYear - publishedYear.get(Calendar.YEAR) > 70) {
      return Answer.YES;
    } else {
      return Answer.NO;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getAssumptionForLastAnswer() {
    return null;
  }

}
