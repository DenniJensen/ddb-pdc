package de.ddb.pdc.answerer;

import java.util.Calendar;

import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the AUTHOR_DIED_MORE_THAN_70_YEARS_AGO question.
 *
 * TODO include month and day in the check.
 */
public class AnswererImplADMT70YA implements Answerer {

  @Override
  public Answer getAnswer(DDBItem metaData) {
    Calendar calendar = Calendar.getInstance();
    int currentYear = calendar.get(Calendar.YEAR);
    if (currentYear - metaData.getAuthorYearOfBirth() > 70) {
      return Answer.YES;
    } else {
      return Answer.NO;
    }
  }

}
