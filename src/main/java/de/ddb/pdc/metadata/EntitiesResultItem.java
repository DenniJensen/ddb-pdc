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
    String[] splitDob = dateOfBirth.split(" ");

    return Integer.parseInt(splitDob[splitDob.length - 1]);
  }

  public int getYearOfDeath() {
    if (dateOfDeath == null) {
      return -1;
    }
    String[] splitDod = dateOfDeath.split(" ");
    return Integer.parseInt(splitDod[splitDod.length - 1]);
  }

  public String getPlaceOfBirth() {
    if (placeOfBirth != null) {
      return placeOfBirth.get(0);
    }
    return null;
  }
}
