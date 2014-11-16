package de.ddb.pdc.answerer.answerers;

import java.util.Calendar;

import de.ddb.pdc.answerer.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.Author;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the PUBLISHED_WITHIN_70_YEARS_OF_DEATH question.
 */
public class PublishedWithin70YearsOfDeathAnswerer implements Answerer {

  /**
   * {@inheritDoc}
   */
  @Override
  public Answer getAnswer(DDBItem metaData) {

    int authorDeathYear = 0;
    for (Author author : metaData.getAuthors()) {
      authorDeathYear = Math.max(authorDeathYear, author.getDeathYear()
          .get(Calendar.YEAR));
    }

    if (metaData.getPublishedYear().get(Calendar.YEAR)
        <= authorDeathYear + 70) {
      return Answer.YES;
    } else {
      return Answer.NO;
    }
  }

}
