package de.ddb.pdc.answerer;

import de.ddb.pdc.core.UnsupportedCategoryException;
import de.ddb.pdc.core.UnsupportedCountryException;
import de.ddb.pdc.metadata.DDBItem;

/**
 *
 *
 */
public interface AnswererService {
  
  /**
   * 
   * @param country
   * @param metadata
   * @return 
   * @throws UnsupportedCountryException 
   * @throws UnsupportedCategoryException 
   * @throws UnsupportedQuestionException 
   */
  public Result getResult(String country, DDBItem metadata)
      throws UnsupportedCountryException, UnsupportedCategoryException,
      UnsupportedQuestionException; 

}
