package de.ddb.pdc.metadata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
class EntitiesResult {

  @JsonProperty
  private ArrayList<EntitiesResultList> results;

  public EntitiesResultItem getResultItem() {
    return results.get(0).getDocs().get(0);
  }
}
