package de.ddb.pdc.metadata;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
class JSONResult {

  private ArrayList<LinkedHashMap> results;

  public ArrayList<LinkedHashMap> getResults() {
    return results;
  }

  
}
