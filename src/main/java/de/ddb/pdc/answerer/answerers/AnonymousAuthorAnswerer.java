package de.ddb.pdc.answerer.answerers;

import java.util.List;

import de.ddb.pdc.answerer.Answerer;
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
      this.note = "No author is known.";
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
