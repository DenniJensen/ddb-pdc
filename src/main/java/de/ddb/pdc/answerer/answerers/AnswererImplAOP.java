package de.ddb.pdc.answerer.answerers;

import de.ddb.pdc.answerer.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the ACT_OF_PARLIAMENT question.
 *
 * FIXME hard coded answer
 */
public class AnswererImplAOP implements Answerer {

  /**
   * {@inheritDoc}
   */
  @Override
  public Answer getAnswer(DDBItem metaData) {
    return Answer.NO;
  }

}
