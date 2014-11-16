package de.ddb.pdc.metadata;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
class ResultsOfJSON {

  private int numberOfResults;
  private ArrayList<SearchResultList> results;
  private EDMItem edm;

  public ArrayList<SearchResultItem> getResults() {
    return results.get(0).getDocs();
  }

  public int getNumberOfResults() {
    return numberOfResults;
  }

  public EDMItem getEdm() {
    return edm;
  }
  
}
