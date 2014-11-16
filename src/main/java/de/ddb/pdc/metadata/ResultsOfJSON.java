package de.ddb.pdc.metadata;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
class ResultsOfJSON {

  private int numberOfResults;
  private ArrayList<SearchResultList> results;
  private EDMItem edm;

  public int getNumberOfResults() {
    return numberOfResults;
  }
  
  public ArrayList<SearchResultItem> getResults() {
    return results.get(0).getDocs();
  }
  
  public EDMItem getEdm() {
    return edm;
  }
  
}
