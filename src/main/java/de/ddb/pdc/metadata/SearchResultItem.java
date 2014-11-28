package de.ddb.pdc.metadata;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * class for a result item of the search json result
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class SearchResultItem {

  private String id;
  private String title;
  private String subtitle;
  private String category;
  private String type;
  private String media;
  private String thumbnail;

  /**
   * Returns the item id of the work.
   */
  public String getId() {
    return id;
  }

  /**
   * Returns the title of the work.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Returns the subtitle of the work.
   */
  public String getSubtitle() {
    return subtitle;
  }

  /**
   * Returns the category of the work.
   */
  public String getCategory() {
    return category;
  }

  /**
   * Returns the type of the work.
   */
  public String getType() {
    return type;
  }

  /**
   * Returns the media of the work.
   */
  public String getMedia() {
    return media;
  }

  /**
   * Returns the relative path to the thumbnail of the work.
   */
  public String getThumbnail() {
    return thumbnail;
  }
}
