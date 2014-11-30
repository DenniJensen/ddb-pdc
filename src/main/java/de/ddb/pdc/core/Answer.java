package de.ddb.pdc.core;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Possible answers to a question. Questions an only be answered with yes or no.
 */
public enum Answer {

  /**
   * Negative answer to a question.
   */
  NO,

  /**
   * Positive answer to a question.
   */
  YES,

  /**
   * The answer to a question is no based on an assumption made by the public
   * domain calculator. Check the note inside the corresponding
   * {@link AnsweredQuestion} of the question trace to learn more about the
   * assumption that was made.
   */
  ASSUMED_NO,

  /**
   * The answer to a question is yes based on an assumption made by the public
   * domain calculator. Check the note inside the corresponding
   * {@link AnsweredQuestion} of the question trace to learn more about the
   * assumption that was made.
   */
  ASSUMED_YES,

  /**
   * The answer to a question is unknown.
   */
  UNKNOWN;

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
    for (int index = 0; index < Answer.values().length; index++) {
      if (Answer.values()[index] == this) {
        return index;
      }
    }
    return -1; // this should never happen
  }

  /**
   * Converts the answer into a string for the json serialization.
   * NO = "no", YES = "yes", ASSUMED_NO = "assumed no",
   * ASSUMED_YES = "assumed yes", UNKNOWN = "unknown"
   *
   * @return the string conversion of the enum
   */
  @Override
  @JsonValue
  public String toString() {
    switch(this) {
      case YES:
        return "yes";
      case ASSUMED_YES:
        return "assumed yes";
      case NO:
        return "no";
      case ASSUMED_NO:
        return "assumed no";
      case UNKNOWN:
        return "unknown";
      default:
        return "";
    }
  }
}
