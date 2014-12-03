package de.ddb.pdc.metadata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
class SearchResults {

  private int numberOfResults;
  @JsonProperty("results")
  private ArrayList<SearchResultList> results;

  /**
   * returns the result of the search json result with the docs.
   */
  public ArrayList<SearchResultItem> getResultItems() {
    return results.get(0).getDocs();
  }
}
