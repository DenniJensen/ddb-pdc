package de.ddb.pdc.answerer.answerers;

import de.ddb.pdc.answerer.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the WORK_PUBLISHED_OR_COMMUNICATED question.
 *
 * TODO hard coded answer.
 */
public class AnswererImplWPOC implements Answerer {

  /**
   * {@inheritDoc}
   */
  @Override
  public Answer getAnswer(DDBItem metaData) {
    return Answer.YES;
  }

}
