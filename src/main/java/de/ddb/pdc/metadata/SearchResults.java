package de.ddb.pdc.metadata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
class SearchResults {

  private int numberOfResults;
  @JsonProperty("results")
  private ArrayList<SearchResultList> results;

  public ArrayList<SearchResultItem> getResultItems() {
    return results.get(0).getDocs();
  }
}
