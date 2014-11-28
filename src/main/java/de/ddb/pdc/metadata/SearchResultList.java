package de.ddb.pdc.metadata;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * class to list the SearchResultItems
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class SearchResultList {

  private ArrayList<SearchResultItem> docs;

  /**
   * Returns a list with all docs of the result item from the search
   * json result.
   */
  public ArrayList<SearchResultItem> getDocs() {
    return docs;
  }
  
}
