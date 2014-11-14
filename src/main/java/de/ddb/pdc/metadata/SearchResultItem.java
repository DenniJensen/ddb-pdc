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

  String getId() {
    return id;
  }

  String getTitle() {
    return title;
  }

  String getSubtitle() {
    return subtitle;
  }

  String getCategory() {
    return category;
  }

  String getType() {
    return type;
  }

  String getMedia() {
    return media;
  }

  String getThumbnail() {
    return thumbnail;
  }
}
