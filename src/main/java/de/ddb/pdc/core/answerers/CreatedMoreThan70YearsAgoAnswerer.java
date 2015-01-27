package de.ddb.pdc.core.answerers;

import java.util.Calendar;

import de.ddb.pdc.core.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;
import de.ddb.pdc.metadata.DdbTimeSpan;

/**
 * Answers the CREATED_MORE_THAN_70_YEARS_AGO question.
 *
 * FIXME Assumption: published year is equal to created year
 */
class CreatedMoreThan70YearsAgoAnswerer implements Answerer {

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
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    this.note = "Es wird angenommen, dass das Jahr der Veröffentlichung ("
        + publishingYearRange.getMaxYear()
        + ") auch das Jahr der Erstellung ist.";
    if (currentYear - publishingYearRange.getMaxYear() > 70) {
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
