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
    return null;
  }
}
