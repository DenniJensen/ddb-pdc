package de.ddb.pdc.core;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

/**
 * Represents the result of a public-domain calculation for a specific item.
 */
public class PDCResult {

  private final boolean publicDomain;
  private final List<AnsweredQuestion> trace;

  /**
   * Creates a PDCResult.
   *
   * @param publicDomain whether the item is considered public-domain
   * @param trace        trace of calculation questions and answers
   */
  public PDCResult(boolean publicDomain, List<AnsweredQuestion> trace) {
    this.publicDomain = publicDomain;
    this.trace = trace;
  }

  /**
   * Returns true if the item in question is considered public-domain by
   * the calculator, or false if not.
   */
  public boolean isPublicDomain() {
    return publicDomain;
  }

  /**
   * Returns all questions and answers that led to the result of the
   * calculation. The questions are returned in the order they were
   * asked.
   */
  public List<AnsweredQuestion> getTrace() {
    return trace;
  }
 
}
