package de.ddb.pdc.answerer;

import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;
import java.util.Calendar;

/**
 * Answers the CREATED_MORE_THAN_70_YEARS_AGO question.
 */
public class AnswererImplCMT70YA implements Answerer {

  @Override
  public Answer getAnswer(DDBItem metaData) {
    Calendar calendar = Calendar.getInstance();
    int currentYear = calendar.get(Calendar.YEAR);
    if (currentYear - metaData.yearOfCreation > 70) {
      return Answer.YES;
    } else {
      return Answer.NO;
    }
  }
  
}
