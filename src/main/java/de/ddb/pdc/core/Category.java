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
 * 
 * @author Frank Zechert
 */
public enum Category {

  /**
   * Literary or artistic work (de: Literatur oder Kunst) Used by Germany
   */
  LITERARY_OR_ARTISTIC_WORK,

  /**
   * Non-original photograph (de: nicht originale Fotografie) Used by Germany
   */
  NON_ORIGINAL_PHOTOGRAPH,

  /**
   * Scientific edition of an out-of-copyright work (de: wissenschaftliche
   * ausgabe einer nicht kopiergeschützten Arbeit) Used by Germany
   */
  SCIENTIFIC_EDITION_OF_AN_OUT_OF_COPYRIGHT_WORK,

  /**
   * Phonogram (de: Tonträger) Used by Germany
   */
  PHONOGRAM,

  /**
   * Broadcast (de: Ausstrahlung, Sendung) Used by Germany
   */
  BROADCAST,

  /**
   * Performance (de: Aufführung, Vorstellung) Used by Germany
   */
  PERFORMANCE,

  /**
   * Unoriginal database (de: nicht originale Datenbank) Used by Germany
   */
  UNORIGINAL_DATABASE,

  /**
   * First fixation of a film (de: erste Aufnahme eines Films) Used by Germany
   */
  FIRST_FIXATION_OF_A_FILM
}
