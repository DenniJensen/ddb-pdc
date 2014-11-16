package de.ddb.pdc.answerer.answerers;

import java.util.Calendar;

import de.ddb.pdc.answerer.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the CREATED_MORE_THAN_70_YEARS_AGO question.
 */
public class CreatedMoreThan70YearsAgoAnswerer implements Answerer {

  /**
   * {@inheritDoc}
   */
  @Override
  public Answer getAnswer(DDBItem metaData) {
    Calendar calendar = Calendar.getInstance();
    int currentYear = calendar.get(Calendar.YEAR);
    if (currentYear - metaData.getYearOfCreation() > 70) {
      return Answer.YES;
    } else {
      return Answer.NO;
    }
  }

}
