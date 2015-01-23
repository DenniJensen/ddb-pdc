package de.ddb.pdc.core.answerers;

import java.util.List;

import de.ddb.pdc.core.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.Author;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the COUNTRY_OF_ORIGIN_TRIPTIS question.
 */
class CountryOfOriginTriptisAnswerer implements Answerer {

  private String note;

  /**
   * Answer whether the country that the item was created in is a member of the
   * Berne Triptis WCT.
   *
   * FIXME this is mis-using the author-nationality.
   */
  @Override
  public Answer answerQuestionForItem(DDBItem metaData) {
    // FIXME wrong nationality used here
    List<Author> authors = metaData.getAuthors();
    if (authors == null || authors.isEmpty()) {
      this.note = "Kein(e) Autor(en) bekannt";
      return Answer.UNKNOWN;
    }

    boolean result = true;
    this.note = "Herkunftsland ist eins oder mehrere der folgendenden: ";
    for (Author author : authors) {
      if (BerneTriptisMembers.isMember(author.getNationality())) {
        this.note += author.getNationality() + " (Mitglied), ";
      } else {
        this.note += author.getNationality() + " (kein Mitglied), ";
        result = false;
      }
    }
    this.note = this.note.substring(0, this.note.length() - 2);
    if (result) {
      return Answer.YES;
    }
    return Answer.NO;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getNoteForLastQuestion() {
    return this.note;
  }
}
