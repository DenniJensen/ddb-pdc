package de.ddb.pdc.metadata;

/**
 * Aggregates the meta data of an item from the DDB.
 * 
 * TODO currently hard coded for testing the Answerer
 */
public class DDBItem {
  
  public final String title;
  public final String category;
  public final String countryCreatedIn;
  public final int yearOfCreation;
  public final int yearPublished;
  
  public final String authorName;
  public final int authorYearOfBirth;
  public final int authorYearOfDeath;
  public final String authorCountry;  
    
  /**
   * 
   * @param title
   * @param category
   * @param countryCreatedIn
   * @param yearOfCreation
   * @param yearPublished
   * @param authorName
   * @param authorYearOfBirth
   * @param authorYearOfDeath
   * @param authorCountry 
   */
  public DDBItem(String title, String category, String countryCreatedIn, 
      int yearOfCreation, int yearPublished, String authorName,
      int authorYearOfBirth, int authorYearOfDeath, String authorCountry) {
    this.title = title;
    this.category = category;
    this.countryCreatedIn = countryCreatedIn;
    this.yearOfCreation = yearOfCreation;
    this.yearPublished = yearPublished;
    this.authorName = authorName;
    this.authorYearOfBirth = authorYearOfBirth;
    this.authorYearOfDeath = authorYearOfDeath;
    this.authorCountry = authorCountry;
  }  
}
