package de.ddb.pdc.metadata;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
class SearchResultList {

  private ArrayList<SearchResultItem> docs;

  public ArrayList<SearchResultItem> getDocs() {
    return docs;
  }
  
}
