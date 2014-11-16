package de.ddb.pdc.metadata;

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
