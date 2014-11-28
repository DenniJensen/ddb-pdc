package de.ddb.pdc.metadata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Class for the entity items of the entity json result
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class EntitiesResultItem {

  private String preferredName;
  @JsonProperty("dateOfBirth_de")
  private String dateOfBirth;
  @JsonProperty("dateOfDeath_de")
  private String dateOfDeath;
  private ArrayList<String> placeOfBirth;

  public String getName() {
    return preferredName;
  }

  /**
   * @return  a 4 digit int if a year is found or -1
   */
  public int getYearOfBirth() {
    if (dateOfBirth == null) {
      return -1;
    }

    try {
      return Integer.parseInt(Utility.useRegex(dateOfBirth,"\\d{4}"));
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  /**
   * @return  a 4 digit int if a year is found or -1
   */
  public int getYearOfDeath() {
    if (dateOfDeath == null) {
      return -1;
    }

    try {
      return Integer.parseInt(Utility.useRegex(dateOfDeath,"\\d{4}"));
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  /**
   * @return the first entry where the author is born or null if empty
   */
  public String getPlaceOfBirth() {
    if (placeOfBirth != null) {
      return placeOfBirth.get(0);
    }
    return null;
  }
}
