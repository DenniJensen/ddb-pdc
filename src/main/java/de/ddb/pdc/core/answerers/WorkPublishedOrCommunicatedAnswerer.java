package de.ddb.pdc.core.answerers;

import de.ddb.pdc.core.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the WORK_PUBLISHED_OR_COMMUNICATED question.
 *
 * TODO hard coded answer.
 */
class WorkPublishedOrCommunicatedAnswerer implements Answerer {

  /**
   * {@inheritDoc}
   */
  @Override
  public Answer answerQuestionForItem(DDBItem metaData) {
    return Answer.ASSUMED_YES;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getNoteForLastQuestion() {
    return "Es wird davon ausgegangen, dass das Werk öffentlich zugänglich "
        + "ist, da es bekannt ist.";
  }

}
