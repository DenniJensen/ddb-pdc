package de.ddb.pdc.core.answerers;

import de.ddb.pdc.core.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the GOVERNMENT_DIRECTIVE question.
 *
 * TODO hard coded answer
 */
class GovernmentDecisionAnswerer implements Answerer {

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
        + "Ein Regierungsbeschluss ist ohne weitere Beschränkungen öffentlich "
        + "zugänglich.";
  }

}
