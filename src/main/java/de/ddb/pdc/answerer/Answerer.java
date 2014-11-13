package de.ddb.pdc.answerer;

import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Interface.
 * 
 */
public interface Answerer {
  
  /**
   * Determine the answer based on the given meta data.
   * @param metaData
   * @return answer
   */
  public Answer getAnswer(DDBItem metaData);

}
