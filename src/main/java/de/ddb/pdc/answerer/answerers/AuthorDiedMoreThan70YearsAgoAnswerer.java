package de.ddb.pdc.answerer.answerers;

import java.util.Calendar;

import de.ddb.pdc.answerer.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.Author;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the AUTHOR_DIED_MORE_THAN_70_YEARS_AGO question.
 *
 * TODO include month and day in the check.
 */
public class AuthorDiedMoreThan70YearsAgoAnswerer implements Answerer {

  /**
   * {@inheritDoc}
   */
  @Override
  public Answer getAnswer(DDBItem metaData) {
    Calendar calendar = Calendar.getInstance();
    int currentYear = calendar.get(Calendar.YEAR);
    int authorDeathYear = 0;
    for (Author author : metaData.getAuthors()) {
      authorDeathYear = Math.max(authorDeathYear, author.getDeathYear()
          .get(Calendar.YEAR));
    }
    if (currentYear - authorDeathYear > 70) {
      return Answer.YES;
    } else {
      return Answer.NO;
    }
  }

}
