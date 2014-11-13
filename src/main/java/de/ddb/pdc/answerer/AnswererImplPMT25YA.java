package de.ddb.pdc.answerer;

import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;
import java.util.Calendar;

/**
 * Answers the PUBLISHED_MORE_THAN_25_YEARS_AGO question.
 */
public class AnswererImplPMT25YA implements Answerer {

  /**
   * 
   * @param metaData
   * @return 
   */
  @Override
  public Answer getAnswer(DDBItem metaData) {
    Calendar calendar = Calendar.getInstance();
    int currentYear = calendar.get(Calendar.YEAR);
    if (currentYear - metaData.yearPublished > 25) {
      return Answer.YES;
    } else {
      return Answer.NO;
    }
  }
  
}
