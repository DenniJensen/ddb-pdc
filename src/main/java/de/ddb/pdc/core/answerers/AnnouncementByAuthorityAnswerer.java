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
    return "Es wird immer davon ausgegangen, dass die Antwort nein ist. "
        + "Eine Bekanntgabe einer öffentlichen Autorität ist ohne "
        + "weitere Beschränkungen öffentlich zugänglich.";
  }

}
