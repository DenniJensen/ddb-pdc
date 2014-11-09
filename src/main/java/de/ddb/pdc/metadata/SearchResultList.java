package de.ddb.pdc.metadata;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * class for list of result items from DDB search
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResultList {
  
  private ArrayList<SearchResultItem> docs;
  
  public ArrayList<SearchResultItem> getDocs() {
    return docs;
  }
}
