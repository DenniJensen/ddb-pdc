package de.ddb.pdc.core.answerers;

import java.util.Calendar;

import de.ddb.pdc.core.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;
import de.ddb.pdc.metadata.DdbTimeSpan;

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
    DdbTimeSpan publishingYearRange = metaData.getPublishingYearRange();
    if (publishingYearRange == null) {
      this.note = "Das Veröffentlichungsdatum ist unbekannt.";
      return Answer.UNKNOWN;
    }

    if (publishingYearRange.getMinYear() == publishingYearRange.getMaxYear()) {
      this.note = "Das Werk wurde "
          + publishingYearRange.getMaxYear()
          + " veröffentlicht.";
    } else {
      this.note = "Das Werk wurde zwischen "
          + publishingYearRange.getMinYear()
          + " und "
          + publishingYearRange.getMaxYear()
          + " veröffentlicht.";
    }

    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    if (currentYear - publishingYearRange.getMaxYear() > 25) {
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
