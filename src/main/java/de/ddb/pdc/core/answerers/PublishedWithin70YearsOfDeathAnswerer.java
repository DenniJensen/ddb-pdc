package de.ddb.pdc.core.answerers;

import java.util.Calendar;
import java.util.List;

import de.ddb.pdc.core.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.Author;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the PUBLISHED_WITHIN_70_YEARS_OF_DEATH question.
 */
class PublishedWithin70YearsOfDeathAnswerer implements Answerer {

  private String note;

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
      if (author.getDateOfDeath() == null
          || !author.getDateOfDeath().isSet(Calendar.YEAR)) {
        this.note = "Not every authors' year of death is known. Assuming "
            + "at least one author is still alive.";
        return Answer.ASSUMED_NO;
      }
      int theYearOfDeath = author.getDateOfDeath().get(Calendar.YEAR);
      authorDeathYear = Math.max(authorDeathYear, theYearOfDeath);
    }

    this.note = null;

    if (metaData.getPublishedYear() == null
        || !metaData.getPublishedYear().isSet(Calendar.YEAR)) {
      this.note = "The year of publication is unknown.";
      return Answer.UNKNOWN;
    }


    int diff = metaData.getPublishedYear().get(Calendar.YEAR) - authorDeathYear;
    this.note = "The work was published in "
        + metaData.getPublishedYear().get(Calendar.YEAR)
        + ". The longest surviving author died in " + authorDeathYear
        + " which is a difference of " + diff + "years.";
    
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
  public String getNoteForLastQuestion() {
    return this.note;
  }

}
