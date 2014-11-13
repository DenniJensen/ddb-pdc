package de.ddb.pdc.answerer;

import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the AUTHOR_ANONYMOUS question.
 */
public class AnswererImplAA implements Answerer {

  /**
   * If {@link DBBItem#author} is null then the author is anonymous.
   * @param metaData
   * @return Answer
   */
  @Override
  public Answer getAnswer(DDBItem metaData) {
    if (metaData.authorName == null) {
      return Answer.YES;
    } else {
      return Answer.NO;
    }
  }
  
}
