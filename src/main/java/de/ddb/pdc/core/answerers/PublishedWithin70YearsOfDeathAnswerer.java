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

  /*
   * assume authors that are missing a death date
   * dead when they are older than 150 years
   */
  private static final int assumeDeathAge = 150;

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
        this.note = "Nicht jedes Sterbedatum der Autoren ist bekannt. Es wird "
            + "angenommen, dass mindestens einer der Autoren noch am Leben "
            + "ist.";
        if (author.getDateOfBirth() != null && author.getDateOfBirth().
            isSet(Calendar.YEAR)) {
          int birthYear = author.getDateOfBirth().get(Calendar.YEAR);
          int currentYear = Calendar.getInstance().get(Calendar.YEAR);
          int age = currentYear - birthYear;
          if (age < assumeDeathAge) {
            return Answer.ASSUMED_NO;
          }
          authorDeathYear = Math.max(authorDeathYear, birthYear
              + assumeDeathAge);
        }
      } else {
        int theYearOfDeath = author.getDateOfDeath().get(Calendar.YEAR);
        authorDeathYear = Math.max(authorDeathYear, theYearOfDeath);
      }
    }

    this.note = null;

    if (metaData.getPublishedYear() == null
        || !metaData.getPublishedYear().isSet(Calendar.YEAR)) {
      this.note = "Das jahr der Veröffentlichung ist unbekannt.";
      return Answer.UNKNOWN;
    }


    int diff = Math.abs(metaData.getPublishedYear().get(Calendar.YEAR)
        - authorDeathYear);
    this.note = "Das Werk wurde "
        + metaData.getPublishedYear().get(Calendar.YEAR)
        + " veröffentlicht. Der Autor, der am längsten überlebt hat, starb "
        + authorDeathYear
        + ". Dies bedeutet, dass " + diff + " Jahre vergangen sind.";

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
