package de.ddb.pdc.metadata;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
class ResultsOfJSON {

  private String numberOfResults;
  private ArrayList<SearchResultList> results;

  ArrayList<SearchResultItem> getResults() {
    return results.get(0).getDocs();
  }

  String getNumberOfResults() {
    return numberOfResults;
  }
}
