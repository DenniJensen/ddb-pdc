package de.ddb.pdc.answerer;

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
  public Answer getAnswer(DDBItem metaData);

}
