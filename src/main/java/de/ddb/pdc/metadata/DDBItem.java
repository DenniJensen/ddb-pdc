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
  private String institution = null;
  private Calendar publishedYear = null;
  private DdbTimeSpan publishingYearRange = null;
  private String ccLicense = null;
 
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

  /**
   * Sets the item's ID.
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Returns the title of the work represented by the item.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets the item's title.
   */
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

  /**
   * Sets the item's subtitle.
   */
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

  /**
   * Sets the item's thumbnail image URL.
   */
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

  /**
   * Sets the item's media type.
   */
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

  /**
   * Sets the item's category.
   */
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

  /**
   * Sets the item's type.
   */
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

  /**
   * Adds an author to the list returned by {@link #getAuthors()}.
   */
  public void addAuthor(Author author) {
    this.authors.add(author);
  }

  /**
   * Returns the institution which has the information about the work.
   * If no institution found, null is returned.
   */
  public String getInstitution() {
    return institution;
  }

  /**
   * Sets the item's institution.
   */
  public void setInstitution(String institution) {
    this.institution = institution;
  }

  /**
   * Returns the published year of the work.
   * If no published year found, null is returned
   */
  public Calendar getPublishedYear() {
    return publishedYear;
  }

  /**
   * Returns the year range within which the item was published. This can
   * be very precise (the exact publishing year is known) or very vague
   * (e.g., some year in the 19th century).
   *
   * @return range of possible publishing years, or null if unknown
   */
  public DdbTimeSpan getPublishingYearRange() {
    return publishingYearRange;
  }

  /**
   * Sets the range of possible publishing years for the item.
   *
   * @param publishingYearRange range of possible publishing years
   */
  public void setPublishingYearRange(DdbTimeSpan publishingYearRange) {
    this.publishingYearRange = publishingYearRange;
  }

  /**
   * Sets the item's publishing year.
   */
  public void setPublishedYear(int publishedYear) {
    if (publishedYear != -1) {
      this.publishedYear = new GregorianCalendar();
      this.publishedYear.set(Calendar.YEAR, publishedYear);
    }
  }

  /**
   * Returns the label of creative common license.
   */
  public String getCcLicense() {
    return ccLicense;
  }

  /**
   * Sets creative common license.
   */
  public void setCcLicense(String ccLicense) {
    this.ccLicense = ccLicense;
  }

  /**
   * Returns if item has a cc license
   */
  public boolean hasCcLicense() {
    return this.ccLicense != null;
  }
}
