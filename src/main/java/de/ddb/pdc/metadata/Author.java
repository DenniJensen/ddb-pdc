package de.ddb.pdc.metadata;

import java.util.Calendar;

/**
 * Stores information about an author of an item.
 */
public class Author {

  private final String dnbId;
  private String name;
  private Calendar dateOfBirth = null;
  private String placeOfBirth;
  private Calendar dateOfDeath = null;
  private String nationality;

  /**
   * Creates an Author.
   *
   * @param dnbId the ID assigned to the author by the DNB
   */
  public Author(String dnbId) {
    this.dnbId = dnbId;
  }

  /**
   * Creates an Author and initializes all fields.
   *
   * @param dnbId the ID assigned to the author by the DNB
   * @param name the name of the author
   * @param dateOfBirth the date the author was born
   * @param placeOfBirth the place the author was born
   * @param dateOfDeath the date the author died
   * @param nationality the nationality of the author
   */
  public Author(String dnbId, String name, Calendar dateOfBirth,
      String placeOfBirth, Calendar dateOfDeath, String nationality) {
    this.dnbId = dnbId;
    this.name = name;
    this.dateOfBirth = dateOfBirth;
    this.placeOfBirth = placeOfBirth;
    this.dateOfDeath = dateOfDeath;
    this.nationality = nationality;
  }

  /**
   * Returns the ID assigned to the author by the 'Deutsche Nationalbibliothek'
   * (http://d-nb.info).
   */
  public String getDnbId() {
    return dnbId;
  }

  /**
   * Returns the author's full name for display.
   */
  public String getName() {
    return name;
  }

  void setName(String name) {
    this.name = name;
  }

  /**
   * Returns the date at which the author was born.
   * If unknown, null is returned.
   */
  public Calendar getDateOfBirth() {
    return dateOfBirth;
  }

  void setDateOfBirth(Calendar dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  /**
   * Returns the date at which the author died. If unknown, null is returned.
   */
  public Calendar getDateOfDeath() {
    return dateOfDeath;
  }

  void setDateOfDeath(Calendar dateOfDeath) {
    this.dateOfDeath = dateOfDeath;
  }

  /**
   * Returns the name of the state at which the author was born.
   * If unknown, null is returned.
   */
  public String getNationality() {
    return nationality;
  }

  void setNationality(String nationality) {
    this.nationality = nationality;
  }

  /**
   * Returns the name of the city or state where the author was born.
   * If unknown, null is returned.
   */
  public String getPlaceOfBirth() {
    return placeOfBirth;
  }

  void setPlaceOfBirth(String placeOfBirth) {
    this.placeOfBirth = placeOfBirth;
  }
}
