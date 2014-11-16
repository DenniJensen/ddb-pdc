package de.ddb.pdc.metadata;

/**
 * Aggregates the metadata of an item from the DDB.
 */
public class DDBItem {
  private String id;
  private String author;
  private String title;
  private String subtitle;
  private String imageUrl;
  private String media;
  private String category;
  private String type;

  /**
   * Creates a new DDBItem.
   *
   * @param id item ID in the DDB databse
   */
  public DDBItem(String id) {
    this.id = id;
  }

  /**
   * Returns the item assigned to the item by the DDB.
   */
  public String getId() {
    return id;
  }

  void setId(String id) {
    this.id = id;
  }

  /**
   * Returns the author of the work represented by the item.
   */
  public String getAuthor() {
    return author;
  }

  void setAuthor(String author) {
    this.author = author;
  }

  /**
   * Returns the title of the work represented by the item.
   */
  public String getTitle() {
    return title;
  }

  void setTitle(String title) {
    this.title = title;
  }

  /**
   * Returns the subtitle (if any) of the work represented by the item.
   * If the work has no subtitle, null is returned.
   */
  public String getSubtitle() {
    return subtitle;
  }

  void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }

  /**
   * Returns the URL of an thumbnail image that can be used when displaying
   * the item in a user interface. The URL is always absolute.
   */
  public String getImageUrl() {
    return imageUrl;
  }

  void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getMedia() {
    return media;
  }

  void setMedia(String media) {
    this.media = media;
  }

  public String getCategory() {
    return category;
  }

  void setCategory(String category) {
    this.category = category;
  }

  public String getType() {
    return type;
  }

  void setType(String type) {
    this.type = type;
  }

}
