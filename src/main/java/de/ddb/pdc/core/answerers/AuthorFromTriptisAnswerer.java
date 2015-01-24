package de.ddb.pdc.core.answerers;

import java.util.List;

import de.ddb.pdc.core.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.Author;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the AUTHOR_FROM_TRIPTIS question.
 */
class AuthorFromTriptisAnswerer implements Answerer {

  private String note;

  /**
   * Answer whether the author's country is a member of the TRIPTIS.
   */
  @Override
  public Answer answerQuestionForItem(DDBItem metaData) {
    List<Author> authors = metaData.getAuthors();
    if (authors == null || authors.isEmpty()) {
      this.note = "Kein(e) Autor(en) bekannt.";
      return Answer.UNKNOWN;
    }
    boolean result = true;
    this.note = "";
    for (Author author : authors) {
      if (author.getNationality() == null) {
        this.note += "Die Nationalität von " + author.getName()
            + " ist unbekannt. Nehme Deutschland an. ";
      } else if (!BerneTriptisMembers.isMember(author.getNationality())) {
        result = false;
        this.note += "Autor " + author.getName() + " ist aus "
            + author.getNationality() + ", welches nicht Teil der Berne "
            + "Triptis WCT ist. ";
      } else {
        this.note += "Autor " + author.getName() + " ist aus "
            + author.getNationality() + ", welches Teil der Berne Triptis "
            + "WCT ist. ";
      }
    }

    this.note = this.note.substring(0, this.note.length() - 1);

    if (result) {
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
