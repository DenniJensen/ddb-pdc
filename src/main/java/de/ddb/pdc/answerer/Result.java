package de.ddb.pdc.answerer;

import de.ddb.pdc.core.AnsweredQuestion;
import java.util.List;

/**
 * Contains the decision on whether the item is public domain or not, including
 * additional information such as the trace and item-related details.
 */
public class Result {
  
  public final boolean isPublicDomain;
  public final List<AnsweredQuestion> trace;
  
  /**
   * 
   * @param isPublicDomain
   * @param trace 
   */
  public Result(boolean isPublicDomain, List<AnsweredQuestion> trace) {
    this.isPublicDomain = isPublicDomain;
    this.trace = trace;
  }
}
