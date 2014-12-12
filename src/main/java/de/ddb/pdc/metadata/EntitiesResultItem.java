package de.ddb.pdc.metadata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

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
   * @return A 4 digit int if a year is found or -1
   */
  public int getYearOfBirth() {
    if (dateOfBirth == null) {
      return -1;
    }

    try {
      return getDateAsInt(dateOfBirth,"\\d{4}");
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  /**
   * @return A 4 digit int if a year is found or -1
   */
  public int getYearOfDeath() {
    if (dateOfDeath == null) {
      return -1;
    }

    try {
      return getDateAsInt(dateOfDeath,"\\d{4}");
    } catch (NumberFormatException e) {
      return -1;
    }
  }
  
  private int getDateAsInt(String date, String regex) {
    return Integer.parseInt(MetadataUtils.useRegex(date, regex));
  }

  /**
   * @return The first entry where the author is born or null if empty
   */
  public String getPlaceOfBirth() {
    if (placeOfBirth != null && placeOfBirth.size() > 0) {
      return placeOfBirth.get(0);
    }
    return null;
  }
}
