package de.ddb.pdc.answerer.answerers;

import de.ddb.pdc.answerer.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the AUTHOR_ANONYMOUS question.
 */
public class AnonymousAauthorAnswerer implements Answerer {

  /**
   * If {@link DDBItem#author} is null then the author is anonymous.
   * @param metaData the metadata of the item.
   * @return Answer the answer.
   */
  @SuppressWarnings("javadoc")
  @Override
  public Answer getAnswer(DDBItem metaData) {
    if (metaData.getAuthor() == null) {
      return Answer.YES;
    } else {
      return Answer.NO;
    }
  }

}
