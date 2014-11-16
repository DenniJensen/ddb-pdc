package de.ddb.pdc.metadata;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Aggregates the metadata of an item from the DDB.
 */
public class DDBItem {
  private String id;
  private String title;
  private String subtitle;
  private String imageUrl;
  private String media;
  private String category;
  private String type;

  //metadata
  private ArrayList<Author> authors;
  private String institute;
  private Calendar publishedYear = new GregorianCalendar();
 
  /**
   * Creates a new DDBItem.
   *
   * @param id item ID in the DDB databse
   */
  public DDBItem(String id) {
    this.id = id;
    this.authors = new ArrayList<Author>();
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

  public ArrayList<Author> getAuthors() {
    return authors;
  }

  public void setAuthor(Author author) {
    this.authors.add(author);
  }

  public String getInstitute() {
    return institute;
  }

  public void setInstitute(String institute) {
    this.institute = institute;
  }

  public Calendar getPublishedYear() {
    return publishedYear;
  }

  public void setPublishedYear(int publishedYear) {
    this.publishedYear.set(Calendar.YEAR, publishedYear);
  }

}
