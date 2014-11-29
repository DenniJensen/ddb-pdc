package de.ddb.pdc.answerer.answerers;

import java.util.Calendar;
import java.util.List;

import de.ddb.pdc.answerer.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.Author;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the AUTHOR_DIED_MORE_THAN_70_YEARS_AGO question.
 *
 * TODO include month and day in the check.
 */
class AuthorDiedMoreThan70YearsAgoAnswerer implements Answerer {

  private String assumption;

  /**
   * {@inheritDoc}
   */
  @Override
  public Answer answerQuestionForItem(DDBItem metaData) {
    List<Author> authors = metaData.getAuthors();
    if (authors == null || authors.isEmpty()) {
      return Answer.UNKNOWN;
    }
    Calendar calendar = Calendar.getInstance();
    int currentYear = calendar.get(Calendar.YEAR);
    int authorDeathYear = 0;
    for (Author author : authors) {
      Calendar deathYearCalendar = author.getYearOfDeath();
      if (deathYearCalendar == null) {
        this.assumption = "Not all death dates known. Will assume some authors "
            + "are still living.";
        return Answer.ASSUMED_NO;
      }

      authorDeathYear = Math.max(authorDeathYear,
          deathYearCalendar.get(Calendar.YEAR));
    }
    if (currentYear - authorDeathYear > 70) {
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
    return this.assumption;
  }
}
