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
  private ArrayList<Author> authors = null;
  private String institute = null;
  private Calendar publishedYear = null;
 
  /**
   * Creates a new DDBItem.
   *
   * @param id item ID in the DDB database
   */
  public DDBItem(String id) {
    this.id = id;
    this.authors = new ArrayList<Author>();
  }

  /**
   * Returns the item ID assigned to the item by the DDB.
   */
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  /**
   * Returns the title of the work represented by the item.
   */
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Returns the subtitle (if any) of the work represented by the item.
   * If the work has no subtitle, null is returned.
   */
  public String getSubtitle() {
    return subtitle;
  }

  public void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }

  /**
   * Returns the URL of a thumbnail image that can be used when displaying
   * the item in a user interface. The URL is always absolute.
   */
  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  /**
   * Returns the Media category of the work.
   * If the work has no media, null is returned.
   */
  public String getMedia() {
    return media;
  }

  public void setMedia(String media) {
    this.media = media;
  }

  /**
   * Returns the category of the work.
   * If the work has no category, null is returned.
   */
  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  /**
   * Returns the type of the work.
   * If the work has no type, null is returned.
   */
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  /**
   * Returns list of authors of the work.
   * If no authors are found, null is returned.
   */
  public ArrayList<Author> getAuthors() {
    return authors;
  }

  public void addAuthor(Author author) {
    this.authors.add(author);
  }

  /**
   * Returns the institution which has the information about the work.
   * If no institution found, null is returned.
   */
  public String getInstitute() {
    return institute;
  }

  public void setInstitute(String institute) {
    this.institute = institute;
  }

  /**
   * Returns the published year of the work.
   * If no published year found, null is returned
   */
  public Calendar getPublishedYear() {
    return publishedYear;
  }

  public void setPublishedYear(int publishedYear) {
    if (publishedYear != -1) {
      this.publishedYear = new GregorianCalendar();
      this.publishedYear.set(Calendar.YEAR, publishedYear);
    }
  }

}
