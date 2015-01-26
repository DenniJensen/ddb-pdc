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
      "Ist dieses Werk in irgendeiner Art von der amtlichen Stelle "
          + "beabsichtigt worden öffentlich zugänglich zu sein?"),

  /**
   * Is the work a court decision or officially issued discussion formula?
   */
  COURT_DECISION_OR_DECISION_FORMULA(
      "Ist das Werk ein Gerichtsurteil oder ein offiziell veröffentlichter "
          + "Beschluss?"),

  /**
   * Is the work an act of parliament which has entered the parliamentary
   * process?
   */
  ACT_OF_PARLIAMENT(
      "Ist dieses Werk ein vom parlament beschlossenes Gesetz?"),

  /**
   * Is the work a government directive?
   */
  GOVERNMENT_DIRECTIVE("Ist das Werk ein Regierungsbeschluss?"),

  /**
   * Is the work an official decision or announcement by a public authority?
   */
  ANNOUNCEMENT_BY_AUTHORITY(
      "Ist dieses Werk eine offizielle Entscheidung oder eine Bekanntgabe einer"
          + " öffentlichen Autorität?"),

  /**
   * Is the author natural person?
   */
  AUTHOR_NATURAL_PERSON("Ist der Autor eine natürliche Person?"),

  /**
   * Is the nationality or place of residence of the author the European
   * Economic Area?
   */
  AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA(
      "Ist die Staatsangehörigkeit oder der Wohnort des Autors der "
          + "Europäische Wirtschaftsraum?"),

  /**
   * Has the work been published or communicated to the public?
   */
  WORK_PUBLISHED_OR_COMMUNICATED(
      "Wurde das Werk veröffentlicht?"),

  /**
   * Was the work published within 70 years of the death of the last surviving
   * author?
   */
  PUBLISHED_WITHIN_70_YEARS_OF_DEATH(
      "Wurde dieses Werk innerhalb von 70 Jahren nach dem Tod des letzten "
          + "überlebenden Autor veröffentlicht?"),

  /**
   * Did the last surviving author die more than 70 years ago?
   */
  AUTHOR_DIED_MORE_THAN_70_YEARS_AGO(
      "Ist der letzte überlebende Autor seit mehr als 70 Jahre tot?"),

  /**
   * Was the work published or communicated to the public more than 25 years
   * ago?
   */
  PUBLISHED_MORE_THAN_25_YEARS_AGO(
      "Ist das Werk schon mehr als 25 Jahre veröffentlicht worden?"),

  /**
   * Was the work published or communicated to the public more than 50 years
   * ago?
   */
  PUBLISHED_MORE_THAN_50_YEARS_AGO(
      "Ist das Werk schon mehr als 50 Jahre veröffentlicht worden?"),

  /**
   * Is the country of origin the European Economic Area?
   */
  COUNTRY_OF_ORIGIN_EEA("Ist das Herkunftsland der Europäische "
      + "Wirtschaftsraum?"),

  /**
   * Is the country of origin the Berne TRIPTIS WCT?
   */
  COUNTRY_OF_ORIGIN_TRIPTIS("Ist das Herkunftsland das Berne TRIPTIS WCT?"),

  /**
   * Is the nationality or place of residence of the author the Berne TRIPTIS
   * WCT?
   */
  AUTHOR_FROM_TRIPTIS(
      "Ist die Staatsangehörigkeit oder der Wohnort des Autors das Berne "
          + "TRIPTIS WCT?"),

  /**
   * The author is anonymous or pseudonymous?
   */
  AUTHOR_ANONYMOUS("Handelt es sich um einen anonymen oder pseudonymen Autor?"),

  /**
   * Was the work published more than 70 years after creation?
   */
  PUBLISHED_MORE_THAN_70_YEAR_AFTER_CREATION(
      "Wurde das Werk nach mehr als 70 Jahren nach Erstellung veröffentlicht?"),

  /**
   * Was the work published within 50 years after creation?
   */
  PUBLISHED_WITHIN_50_YEAR_AFTER_CREATION(
      "Wurde das Werk innerhalb von 50 Jahren nach Erstellung veröffentlicht?"),

  /**
   * Was the work published more than 70 years ago?
   */
  PUBLISHED_MORE_THAN_70_YEARS_AGO(
      "Ist das Werk vor mehr als 70 Jahren veröffentlicht worden?"),

  /**
   * Was the work created more than 70 years ago?
   */
  CREATED_MORE_THAN_70_YEARS_AGO(
      "Ist das Werk vor mehr als 70 Jahren erstellt worden?"),

  /**
   * Was the work created more than 50 years ago?
   */
  CREATED_MORE_THAN_50_YEARS_AGO(
      "Ist das Werk vor mehr als 50 Jahren erstellt worden?"),

  /**
   * Was the work created more than 50 years ago?
   */
  CREATED_MORE_THAN_25_YEARS_AGO("Ist das Werk vor mehr als 25 Jahren erstellt "
      + "worden?"),

  /**
   * Was the programme first broadcast more than 25 years ago?
   */
  FIRST_BROADCAST_25_YEARS(
      "Wurde die Sendung zum ersten Mal vor mehr als 25 Jahren ausgestrahlt?"),

  /**
   * Was a fixation of the performance published or communicated to the public
   * within 50 years from the date of the performance?
   */
  FIXATION_PUBLISHED_50_YEARS_FROM_PERFORMANCE(
      "Wurde die Aufzeichnung innerhalb von 50 Jahren nach der ersten "
          + "Ausstrahlung veröffentlicht?"),

  /**
   * Was the fixation published or communicated to the public more than 50 years
   * ago?
   */
  FIXATION_PUBLISHED_MORE_THAN_50_YEARS_AGO(
      "Wurde die Aufzeichnung innerhalb vor mehr als 50 Jahren "
          + "veröffentlicht?"),

  /**
   * Did the performance take place more than 50 years ago?
   */
  PERFORMED_MORE_THAN_50_YEARS_AGO(
      "Hat die Ausstrahlung vor mehr als 50 Jahren stattgefunden?"),

  /**
   * Is the rightsholder a company or firm?
   */
  RIGHT_HOLDER_COMPANY_OR_FIRM("Ist der Rechteinhaber ein Unternehmen oder "
      + "eine Firma?"),

  /**
   * Was the work formed under the law of the European Economic Area?
   */
  FORMED_UNDER_LAW_OF_EEA(
      "Wurde das Werk innerhalb der Gesetzlage des Europäischen "
          + "Wirtschaftsraums erstellt?"),

  /**
   * Does it have its registered office, central administration or principal
   * place of business inside the European Economic Area?
   */
  REGISTERED_IN_EEA(
      "Befindet sich der Sitz, die zentrale Verwaltung oder die "
          + "Hauptniederlassung innerhalb des Europäischen Wirtschaftsraums?"),

  /**
   * Was the work published after it was completed or after its last substantial
   * change?
   */
  PUBLISHED_AFTER_COMPLETION_OR_CHANGE(
      "Wurde das Werk nach seiner Vollendung oder einer maßgeblichen Änderung "
          + "veröffentlicht?"),

  /**
   * Was the work published within the last 15 years?
   */
  PUBLISHED_IN_LAST_15_YEARS("Wurde das Werk innerhalb der letzten 15 Jahren "
          + "veröffentlicht?"),

  /**
   * Was the work completed or substantially changed within the last 15 years?
   */
  CHANGED_OR_COMPLETED_WITHIN_LAST_15_YEARS(
      "Wurde das Werk innerhalb der letzten 15 Jahren vollendet oder maßgeblich"
          + " geändert?"),

  /**
   * Has a registered office in the European Economic Area?
   */
  COUNTRY_REGISTERED_OFFICE_EEA(
      "Hat es einen Sitz im Europäischen Wirtschaftsraum?"),

  /**
   * Are its operations genuinely linked on an ongoing basis with the economy of
   * an European Economic Area State?
   */
  OPERATION_LINKED_TO_ECONOMY_EEA(
      "Sind seine Tätigkeiten dauerhaft mit dem Europäischen Wirtschaftsraum "
          + "verknüpft?"),

  /**
   * Is the rights holder a national or resident of an European Economic Area
   * Sate?
   */
  RIGHTSHOLDER_RESIDENT_EEA(
      "Ist der Recheinhaber ein Staatsangehöriger oder Bewohner des "
          + "Europäischen Wirtscahftsraums?"),

  /**
   * Fake question for special case: calculation failed, inform front-end
   */
  CALCULATION_FAILED("Die Berechnung is fehlgeschlagen."),

  /**
   * Fake question for special case: item is already public domain
   */
  IS_PUBLIC_DOMAIN("Das Werk ist bereits freigegeben."),

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
