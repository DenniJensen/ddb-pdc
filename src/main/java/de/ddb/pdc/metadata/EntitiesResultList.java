package de.ddb.pdc.metadata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
class EntitiesResultList {

  private ArrayList<EntitiesResultItem> docs;

  public ArrayList<EntitiesResultItem> getDocs() {
    return docs;
  }
}
