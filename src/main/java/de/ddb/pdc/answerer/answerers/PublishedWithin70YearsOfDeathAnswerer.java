package de.ddb.pdc.answerer.answerers;

import java.util.Calendar;
import java.util.List;

import de.ddb.pdc.answerer.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.Author;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the PUBLISHED_WITHIN_70_YEARS_OF_DEATH question.
 */
class PublishedWithin70YearsOfDeathAnswerer implements Answerer {

  private String assumption;

  /**
   * {@inheritDoc}
   */
  @Override
  public Answer answerQuestionForItem(DDBItem metaData) {
    List<Author> authors = metaData.getAuthors();
    Calendar publishedYear = metaData.getPublishedYear();
    if (authors == null || authors.isEmpty() || publishedYear == null
        || !publishedYear.isSet(Calendar.YEAR)) {
      return Answer.UNKNOWN;
    }

    int authorDeathYear = 0;
    for (Author author : authors) {
      if (author.getYearOfDeath() == null
          || !author.getYearOfDeath().isSet(Calendar.YEAR)) {
        this.assumption = "Not all death years of all authors known. Assuming "
            + "at least one author is still alive";
        return Answer.ASSUMED_NO;
      }
      int theYearOfDeath = author.getYearOfDeath().get(Calendar.YEAR);
      authorDeathYear = Math.max(authorDeathYear, theYearOfDeath);
    }

    this.assumption = null;

    if (metaData.getPublishedYear() == null
        || !metaData.getPublishedYear().isSet(Calendar.YEAR)) {
      return Answer.UNKNOWN;
    }

    if (metaData.getPublishedYear().get(Calendar.YEAR)
        <= authorDeathYear + 70) {
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
