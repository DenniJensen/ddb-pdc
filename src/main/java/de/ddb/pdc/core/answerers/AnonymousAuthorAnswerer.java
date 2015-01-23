package de.ddb.pdc.core.answerers;

import java.util.List;

import de.ddb.pdc.core.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.Author;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the AUTHOR_ANONYMOUS question.
 */
class AnonymousAuthorAnswerer implements Answerer {

  private String note;

  /**
   * If {@link DDBItem#author} is null then the author is anonymous.
   * @param metaData the metadata of the item.
   * @return Answer the answer.
   */
  @SuppressWarnings("javadoc")
  @Override
  public Answer answerQuestionForItem(DDBItem metaData) {
    List<Author> authors = metaData.getAuthors();
    if (authors == null || authors.isEmpty()) {
      note = "Kein Autor ist bekannt. Deshalb wird davon ausgegangen, dass es "
          + "sich um einen anonymen Autor handelt.";
      return Answer.YES;
    } else {
      this.note = "Folgende Autoren wurden gefunden: ";
      for (Author author : authors) {
        this.note += author.getName() + ", ";
      }
      this.note = this.note.substring(0, this.note.length() - 2);
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
