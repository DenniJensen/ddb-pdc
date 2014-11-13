package de.ddb.pdc.answerer;

import de.ddb.pdc.core.Question;

/**
 * An unsupported question was used. This error is thrown to indicate that you
 * passed a question to the {@link AnswererFactory} that is not supported by the
 * implementation you are using. Inspect the {@link Question} class to view
 * which questions are supported.
 */
public class UnsupportedQuestionException extends Exception {

  private static final long serialVersionUID = 500777465790621544L;
  
  /**
   * The question that was unsupported and caused this exception.
   */
  private final Question unsupportedQuestion;

  /**
   * Create a new UnsupportedQuestionException.
   *
   * @param unsupportedQuestion The unsupported question that caused this
   *        exception.
   */
  public UnsupportedQuestionException(final Question unsupportedQuestion) {
    this.unsupportedQuestion = unsupportedQuestion;
  }

  /**
   * Gets the question that was unsupported and caused this exception
   *
   * @return the unsupported question
   */
  public Question getUnsupportedQuestion() {
    return this.unsupportedQuestion;
  }
}