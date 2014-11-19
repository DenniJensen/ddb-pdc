package de.ddb.pdc.metadata;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
class SearchResultItem {

  private String id;
  private String title;
  private String subtitle;
  private String category;
  private String type;
  private String media;
  private String thumbnail;
  
  // for the data about the author
  private String dateOfBirth;
  private String dateOfDeath;
  private ArrayList<String> placeOfBirth;
  private ArrayList<String> placeOfDeath;

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getSubtitle() {
    return subtitle;
  }

  public String getCategory() {
    return category;
  }

  public String getType() {
    return type;
  }

  public String getMedia() {
    return media;
  }

  public String getThumbnail() {
    return thumbnail;
  }
}
