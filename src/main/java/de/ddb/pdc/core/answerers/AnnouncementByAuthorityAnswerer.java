package de.ddb.pdc.core.answerers;

import de.ddb.pdc.core.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the ANNOUNCEMENT_BY_AUTHORITY question.
 *
 * FIXME hard coded answer
 */
class AnnouncementByAuthorityAnswerer implements Answerer {

  /**
   * {@inheritDoc}
   */
  @Override
  public Answer answerQuestionForItem(DDBItem metaData) {
    return Answer.ASSUMED_NO;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getNoteForLastQuestion() {
    return "The answer is always assumed to be no. An announcement by a public "
        + "authority always falls into the public domain without further "
        + "limitations.";
  }

}
