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
  YES;

  /**
   * Converts the answer to a boolean by returning true for YES and false
   * for NO. Used for JSON serialization.
   */
  @JsonValue
  public boolean toBoolean() {
    return this == YES;
  }
}
