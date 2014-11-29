package de.ddb.pdc.core;

import de.ddb.pdc.core.Answer;
import de.ddb.pdc.core.Question;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Interface to the Answerer classes. There will be one implementation of this
 * interface for each Question in the {@link Question} enum.
 */
public interface Answerer {

  /**
   * Determine the answer to a {@link Question} question using the metadata of
   * a database item.
   *
   * @param metaData The metadata to decide the {@link Question} question on.
   * @return answer The answer to the question.
   */
  public Answer answerQuestionForItem(DDBItem metaData);

  /**
   * Gets the assumptions that have been made while answering the question
   * for the previous item. Not all Questions can be answered based on known
   * facts of the item that was passed to the answerQuestionForItem method.
   * If metadata is missing or not clear enough to answer the question,
   * the answerer can make an assumption to solve the question for this item.
   * If an assumption was made to answer the question this assumption can be
   * accessed by calling this method. It will return null if no assumptions have
   * been made.
   *
   * @return null if no assumptions have been made or the assumption itself
   */
  public String getAssumptionForLastAnswer();

}
