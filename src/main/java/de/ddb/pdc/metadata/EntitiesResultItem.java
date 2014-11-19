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

  public int getYearOfBirth() {
    if (dateOfBirth == null) {
      return -1;
    }
    return Integer.parseInt(dateOfBirth.split(" ")[2]);
  }

  public int getYearOfDeath() {
    if (dateOfDeath == null) {
      return -1;
    }
    return Integer.parseInt(dateOfDeath.split(" ")[2]);
  }

  public String getPlaceOfBirth() {
    return placeOfBirth.get(0);
  }
}
