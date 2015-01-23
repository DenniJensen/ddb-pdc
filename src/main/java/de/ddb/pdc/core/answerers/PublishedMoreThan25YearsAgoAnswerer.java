package de.ddb.pdc.core.answerers;

import java.util.Calendar;

import de.ddb.pdc.core.Answerer;
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
    this.note = "Das Werk wurde "
        + publishedYear.get(Calendar.YEAR)
        + "veröffentlicht.";
    if (currentYear - publishedYear.get(Calendar.YEAR) > 25) {
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
