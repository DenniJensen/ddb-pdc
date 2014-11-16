package de.ddb.pdc.metadata;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Class store Author informations
 */
public class Author {

  private String dnbId;
  private String name;
  private Calendar birthYear = new GregorianCalendar();
  private Calendar deathYear = new GregorianCalendar();
  private String nationality;
  
  public Author(String dnbId) {
    this.dnbId = dnbId;
  }
  
  public String getDnbId() {
    return dnbId;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public Calendar getBirthYear() {
    return birthYear;
  }
  
  public void setBirthYear(int birthYear) {
    this.birthYear.set(Calendar.YEAR, birthYear);;
  }
  
  public Calendar getDeathYear() {
    return deathYear;
  }
  
  public void setDeathYear(int deathYear) {
    this.deathYear.set(Calendar.YEAR, deathYear);;
  }
  
  public String getNationality() {
    return nationality;
  }
  
  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

}
