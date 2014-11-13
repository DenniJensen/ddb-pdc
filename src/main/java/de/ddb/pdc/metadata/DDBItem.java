package de.ddb.pdc.metadata;

/**
 * Aggregates the meta data of an item from the DDB.
 * 
 * TODO currently hard coded for testing the Answerer
 */
public class DDBItem {
  
  public String title = "I am a fairy";
  public String category = "LITERARY_OR_ARTISTIC_WORK";
  public String countryCreatedIn = "Austria";
  public int yearOfCreation = 1940;
  public int yearPublished = 1956;
  
  public String authorName = "Göthe";
  public int authorYearOfBirth = 1930;
  public int authorYearOfDeath = 1930;
  public String authorCountry = "Austria";  
  
  /**
   * 
   */ 
  public DDBItem(){
    
  }  
}
