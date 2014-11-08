package de.ddb.pdc.core;

/**
 * List of categories.
 *
 * This enumeration contains all categories of cultural goods that are supported
 * by public domain calculator implementations.
 *
 * Note that not every public domain calculator implementation needs to support
 * all of these categories. To know which categories are supported by your
 * implementation of the public domain calculator call the
 * getSupportedCategories() method of your public domain calculator
 * implementation.
 */
public enum Category {

  /**
   * Literary or artistic work (german: "Literatur oder Kunst").
   */
  LITERARY_OR_ARTISTIC_WORK,

  /**
   * Non-original photograph (german: "nicht originale Fotografie").
   */
  NON_ORIGINAL_PHOTOGRAPH,

  /**
   * Scientific edition of an out-of-copyright work (german: "wissenschaftliche
   * ausgabe einer nicht kopiergeschützten Arbeit").
   */
  SCIENTIFIC_EDITION_OF_AN_OUT_OF_COPYRIGHT_WORK,

  /**
   * Phonogram (german: "Tonträger").
   */
  PHONOGRAM,

  /**
   * Broadcast (german: "Ausstrahlung, Sendung").
   */
  BROADCAST,

  /**
   * Performance (german: "Aufführung, Vorstellung").
   */
  PERFORMANCE,

  /**
   * Unoriginal database (german: "nicht originale Datenbank").
   */
  UNORIGINAL_DATABASE,

  /**
   * First fixation of a film (german: "erste Aufnahme eines Films").
   */
  FIRST_FIXATION_OF_A_FILM
}
