package de.ddb.pdc.core;

import java.util.List;

/**
 * Represents the result of a public-domain calculation for a specific item.
 */
public class PDCResult {

  private final Boolean publicDomain;
  private final List<AnsweredQuestion> trace;

  /**
   * Creates a PDCResult.
   *
   * @param publicDomain whether the item is considered public-domain
   * @param trace        trace of calculation questions and answers
   */
  public PDCResult(Boolean publicDomain, List<AnsweredQuestion> trace) {
    this.publicDomain = publicDomain;
    this.trace = trace;
  }

  /**
   * Returns true if the item in question is considered public-domain by
   * the calculator, or false if not. Returns null if the status could not be
   * determined.
   *
   * @return true or false if the item is or is not in the public domain. null
   *         if the decision could not be made.
   */
  public Boolean isPublicDomain() {
    return publicDomain;
  }

  /**
   * Returns all questions and answers that led to the result of the
   * calculation. The questions are returned in the order they were
   * asked.
   *
   * @return A list of all asked questions and the given answer.
   */
  public List<AnsweredQuestion> getTrace() {
    return trace;
  }

}
