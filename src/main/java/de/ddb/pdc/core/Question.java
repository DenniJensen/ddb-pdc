package de.ddb.pdc.core;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Questions that can be asked by the public domain calculator.
 *
 * This enumeration contains all the questions that can be part of the
 * questionnaires. Not every questionnaire needs to use all of these questions
 * nor does an implementation of a public domain calculator. Instead they can
 * use a subset of these questions.
 */
@SuppressWarnings("nls")
public enum Question {

  /**
   * Is the work any kind of work which the official body publishing it intends
   * to be generally received?
   */
  OFFICIAL_WORK_INTENDED_TO_BE_GENERALLY_RECEIVED(
      "Is the work any kind of work which the official body publishing it "
          + "intends to be generally received?"),

  /**
   * Is the work a court decision or officially issued discussion formula?
   */
  COURT_DECISION_OR_DECISION_FORMULA(
      "Is the work a court decision or officially issued discussion formula?"),

  /**
   * Is the work an act of parliament which has entered the parliamentary
   * process?
   */
  ACT_OF_PARLIAMENT(
      "Is the work an act of parliament which has entered the parliamentary "
          + "process?"),

  /**
   * Is the work a government directive?
   */
  GOVERNMENT_DIRECTIVE("Is the work a government directive?"),

  /**
   * Is the work an official decision or announcement by a public authority?
   */
  ANNOUNCEMENT_BY_AUTHORITY(
      "Is the work an official decision or announcement by a public "
          + "authority?"),

  /**
   * Is the author natural person?
   */
  AUTHOR_NATURAL_PERSON("Is the author natural person?"),

  /**
   * Is the nationality or place of residence of the author the European
   * Economic Area?
   */
  AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA(
      "Is the nationality or place of residence of the author the European "
          + "Economic Area?"),

  /**
   * Has the work been published or communicated to the public?
   */
  WORK_PUBLISHED_OR_COMMUNICATED(
      "Has the work been published or communicated to the public?"),

  /**
   * Was the work published within 70 years of the death of the last surviving
   * author?
   */
  PUBLISHED_WITHIN_70_YEARS_OF_DEATH(
      "Was the work published within 70 years of the death of the last "
          + "surviving author?"),

  /**
   * Did the last surviving author die more than 70 years ago?
   */
  AUTHOR_DIED_MORE_THAN_70_YEARS_AGO(
      "Did the last surviving author die more than 70 years ago?"),

  /**
   * Was the work published or communicated to the public more than 25 years
   * ago?
   */
  PUBLISHED_MORE_THAN_25_YEARS_AGO(
      "Was the work published or communicated to the public more than 25 "
          + "years ago?"),

  /**
   * Was the work published or communicated to the public more than 50 years
   * ago?
   */
  PUBLISHED_MORE_THAN_50_YEARS_AGO(
      "Was the work published or communicated to the public more than 50 "
          + "years ago?"),

  /**
   * Is the country of origin the European Economic Area?
   */
  COUNTRY_OF_ORIGIN_EEA("Is the country of origin the European Economic Area?"),

  /**
   * Is the country of origin the Berne TRIPTIS WCT?
   */
  COUNTRY_OF_ORIGIN_TRIPTIS("Is the country of origin the Berne TRIPTIS WCT?"),

  /**
   * Is the nationality or place of residence of the author the Berne TRIPTIS
   * WCT?
   */
  AUTHOR_FROM_TRIPTIS(
      "Is the nationality or place of residence of the author the Berne "
          + "TRIPTIS WCT?"),

  /**
   * The author is anonymous or pseudonymous?
   */
  AUTHOR_ANONYMOUS("The author is anonymous or pseudonymous?"),

  /**
   * Was the work published more than 70 years after creation?
   */
  PUBLISHED_MORE_THAN_70_YEAR_AFTER_CREATION(
      "Was the work published more than 70 years after creation?"),

  /**
   * Was the work published within 50 years after creation?
   */
  PUBLISHED_WITHIN_50_YEAR_AFTER_CREATION(
      "Was the work published within 50 years after creation?"),

  /**
   * Was the work published more than 70 years ago?
   */
  PUBLISHED_MORE_THAN_70_YEARS_AGO(
      "Was the work published more than 70 years ago?"),

  /**
   * Was the work created more than 70 years ago?
   */
  CREATED_MORE_THAN_70_YEARS_AGO("Was the work created more than 70 years "
      + "ago?"),

  /**
   * Was the work created more than 50 years ago?
   */
  CREATED_MORE_THAN_50_YEARS_AGO("Was the work created more than 50 years "
      + "ago?"),

  /**
   * Was the work created more than 50 years ago?
   */
  CREATED_MORE_THAN_25_YEARS_AGO("Was the work created more than 25 years "
      + "ago?"),

  /**
   * Was the programme first broadcast more than 25 years ago?
   */
  FIRST_BROADCAST_25_YEARS(
      "Was the programme first broadcast more than 25 years ago?"),

  /**
   * Was a fixation of the performance published or communicated to the public
   * within 50 years from the date of the performance?
   */
  FIXATION_PUBLISHED_50_YEARS_FROM_PERFORMANCE(
      "Was a fixation of the performance published or communicated to the "
          + "public within 50 years from the date of the performance?"),

  /**
   * Was the fixation published or communicated to the public more than 50 years
   * ago?
   */
  FIXATION_PUBLISHED_MORE_THAN_50_YEARS_AGO(
      "Was the fixation published or communicated to the public more than 50 "
          + "years ago?"),

  /**
   * Did the performance take place more than 50 years ago?
   */
  PERFORMED_MORE_THAN_50_YEARS_AGO(
      "Did the performance take place more than 50 years ago?"),

  /**
   * Is the rightsholder a company or firm?
   */
  RIGHT_HOLDER_COMPANY_OR_FIRM("Is the rightsholder a company or firm?"),

  /**
   * Was the work formed under the law of the European Economic Area?
   */
  FORMED_UNDER_LAW_OF_EEA(
      "Was the work formed under the law of the European Economic Area?"),

  /**
   * Does it have its registered office, central administration or principal
   * place of business inside the European Economic Area?
   */
  REGISTERED_IN_EEA(
      "Does it have its registered office, central administration or principal "
          + "place of business inside the European Economic Area?"),

  /**
   * Was the work published after it was completed or after its last substantial
   * change?
   */
  PUBLISHED_AFTER_COMPLETION_OR_CHANGE(
      "Was the work published after it was completed or after its last "
          + "substantial change?"),

  /**
   * Was the work published within the last 15 years?
   */
  PUBLISHED_IN_LAST_15_YEARS("Was the work published within the last 15 "
      + "years?"),

  /**
   * Was the work completed or substantially changed within the last 15 years?
   */
  CHANGED_OR_COMPLETED_WITHIN_LAST_15_YEARS(
      "Was the work completed or substantially changed within the last 15 "
          + "years?"),

  /**
   * Has a registered office in the European Economic Area?
   */
  COUNTRY_REGISTERED_OFFICE_EEA(
      "Has a registered office in the European Economic Area?"),

  /**
   * Are its operations genuinely linked on an ongoing basis with the economy of
   * an European Economic Area State?
   */
  OPERATION_LINKED_TO_ECONOMY_EEA(
      "Are its operations genuinely linked on an ongoing basis with the "
          + "economy of an European Economic Area State?"),

  /**
   * Is the rights holder a national or resident of an European Economic Area
   * Sate?
   */
  RIGHTSHOLDER_RESIDENT_EEA(
      "Is the rights holder a national or resident of an European Economic "
          + "Area Sate?"),


  ;

  /**
   * The human readable question.
   */
  private String questionText;

  /**
   * Creates a new question.
   *
   * @param questionText The human readable question
   */
  private Question(String questionText) {
    this.questionText = questionText;
  }

  /**
   * Gets the human readable text of the question that (e.g.) can be printed to
   * the user. Used for JSON serialization.
   *
   * @return the human readable text
   */
  @JsonValue
  public String getText() {
    return this.questionText;
  }
}
