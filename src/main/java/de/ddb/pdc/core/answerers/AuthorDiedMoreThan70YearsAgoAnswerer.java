package de.ddb.pdc.core.answerers;

import java.util.Calendar;
import java.util.List;

import de.ddb.pdc.core.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.Author;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the AUTHOR_DIED_MORE_THAN_70_YEARS_AGO question.
 *
 * TODO include month and day in the check.
 */
class AuthorDiedMoreThan70YearsAgoAnswerer implements Answerer {

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
    if (authors == null || authors.isEmpty()) {
      this.note = "Kein(e) Autor(en) bekannt.";
      return Answer.UNKNOWN;
    }
    Calendar calendar = Calendar.getInstance();
    int currentYear = calendar.get(Calendar.YEAR);
    int authorDeathYear = 0;

    this.note = "Nicht alle Sterbedaten sind bekannt. Es wird davon "
        + "ausgegangen, dass manche Autoren noch am Leben sind. Sterbedaten "
        + "unbekannt für ";
    boolean unknown = false;
    String authorDeaths = "";
    for (Author author : authors) {
      Calendar deathYearCalendar = author.getDateOfDeath();
      if (deathYearCalendar == null) {
        if (author.getDateOfBirth() != null && author.getDateOfBirth()
            .isSet(Calendar.YEAR)) {
          int birthYear = author.getDateOfBirth().get(Calendar.YEAR);
          if (currentYear - birthYear > assumeDeathAge) {
            int diedIn = currentYear - birthYear + assumeDeathAge;

            authorDeaths += author.getName() + " starb " + diedIn + ", ";

            authorDeathYear = Math.max(authorDeathYear, diedIn);

          } else {
            this.note += author.getName() + ", ";
            unknown = true;
          }
        } else {
          this.note += author.getName() + ", ";
          unknown = true;
        }
      } else {
        authorDeaths += author.getName() + " starb "
            + author.getDateOfDeath().get(Calendar.YEAR) + ", ";

        authorDeathYear = Math.max(authorDeathYear,
            deathYearCalendar.get(Calendar.YEAR));
      }
    }

    if (unknown) {
      this.note = this.note.substring(0, this.note.length() - 2);
      return Answer.ASSUMED_NO;
    }
    authorDeaths = authorDeaths.substring(0, authorDeaths.length() - 2) + ".";

    if (currentYear - authorDeathYear > 70) {
      this.note = "Alle Autoren starben vor oder in " + authorDeathYear + ": "
          + authorDeaths;
      return Answer.YES;
    } else {
      this.note = "Mindestens ein Autor starb in " + authorDeathYear + ": "
          + authorDeaths;
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
