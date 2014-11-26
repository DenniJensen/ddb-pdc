package de.ddb.pdc.core;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Possible answers to a question. Questions an only be answered with yes or no.
 */
public enum Answer {

  /**
   * Negative answer to a question.
   */
  NO(0),

  /**
   * Positive answer to a question.
   */
  YES(1),

  /**
   * The answer to a question is no based on an assumption made by the public
   * domain calculator. Check the note inside the corresponding
   * {@link AnsweredQuestion} of the question trace to learn more about the
   * assumption that was made.
   */
  ASSUMED_NO(2),

  /**
   * The answer to a question is yes based on an assumption made by the public
   * domain calculator. Check the note inside the corresponding
   * {@link AnsweredQuestion} of the question trace to learn more about the
   * assumption that was made.
   */
  ASSUMED_YES(3),

  /**
   * The answer to a question is yes based on an assumption made by the public
   * domain calculator. Check the note inside the corresponding
   * {@link AnsweredQuestion} of the question trace to learn more about the
   * assumption that was made.
   */
  UNKNOWN(4);

  private int value;

  private Answer(int value) {
    this.value = value;
  }

  /**
   * Converts the answer to an integer used for the json serialization.
   * NO = 0, YES = 1, ASSUMED_NO = 2, ASSUMED_YES = 3, UNKNOWN = 4
   *
   * @return 0 if the answer is no, 1 if the answer is yes, 2 if the answer
   *         is assumed no, 3 if the answer is assumed yes, and 4 if the answer
   *         is unknown
   */
  @JsonValue
  public int toInteger() {
    return this.value;
  }
}
