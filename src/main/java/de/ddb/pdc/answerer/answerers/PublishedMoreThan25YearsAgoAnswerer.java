package de.ddb.pdc.answerer.answerers;

import java.util.Calendar;

import de.ddb.pdc.answerer.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the PUBLISHED_MORE_THAN_25_YEARS_AGO question.
 */
class PublishedMoreThan25YearsAgoAnswerer implements Answerer {

  private String note;

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
    if (currentYear - publishedYear.get(Calendar.YEAR) > 25) {
      this.note = "The work was published in "
          + publishedYear.get(Calendar.YEAR);
      return Answer.YES;
    } else {
      return Answer.NO;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getNoteForLastQuestion() {
    return this.note;
  }
}
