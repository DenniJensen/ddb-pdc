package de.ddb.pdc.answerer.answerers;

import de.ddb.pdc.answerer.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the ANNOUNCEMENT_BY_AUTHORITY question.
 *
 * FIXME hard coded answer
 */
public class AnswererImplABA implements Answerer {

  /**
   * {@inheritDoc}
   */
  @Override
  public Answer getAnswer(DDBItem metaData) {
    return Answer.NO;
  }

}
