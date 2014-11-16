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
  private String countryCreatedIn;
  private int yearOfCreation;
  private int yearPublished;
  private int authorYearOfBirth;
  private int authorYearOfDeath;
  private String authorCountry;

  /**
   * Creates a new DDBItem.
   *
   * @param id item ID in the DDB databse
   */
  public DDBItem(String id) {
    this.id = id;
  }

  /**
   * Creates a new DDBItem. This constructor is only used for test / dummy
   * data
   *
   * @param title
   * @param category
   * @param countryCreatedIn
   * @param yearOfCreation
   * @param yearPublished
   * @param authorName
   * @param authorYearOfBirth
   * @param authorYearOfDeath
   * @param authorCountry
   */
  public DDBItem(String title, String category, String countryCreatedIn,
      int yearOfCreation, int yearPublished, String authorName,
      int authorYearOfBirth, int authorYearOfDeath, String authorCountry) {
    this.title = title;
    this.category = category;
    this.countryCreatedIn = countryCreatedIn;
    this.yearOfCreation = yearOfCreation;
    this.yearPublished = yearPublished;
    this.author = authorName;
    this.authorYearOfBirth = authorYearOfBirth;
    this.authorYearOfDeath = authorYearOfDeath;
    this.authorCountry = authorCountry;
  }

  /**
   * Returns the item assigned to the item by the DDB.
   */
  public String getId() {
    return this.id;
  }

  void setId(String id) {
    this.id = id;
  }

  /**
   * Returns the author of the work represented by the item.
   */
  public String getAuthor() {
    return this.author;
  }

  void setAuthor(String author) {
    this.author = author;
  }

  /**
   * Returns the title of the work represented by the item.
   */
  public String getTitle() {
    return this.title;
  }

  void setTitle(String title) {
    this.title = title;
  }

  /**
   * Returns the subtitle (if any) of the work represented by the item.
   * If the work has no subtitle, null is returned.
   */
  public String getSubtitle() {
    return this.subtitle;
  }

  void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }

  /**
   * Returns the URL of an thumbnail image that can be used when displaying
   * the item in a user interface. The URL is always absolute.
   */
  public String getImageUrl() {
    return this.imageUrl;
  }

  void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getMedia() {
    return this.media;
  }

  void setMedia(String media) {
    this.media = media;
  }

  public String getCategory() {
    return this.category;
  }

  void setCategory(String category) {
    this.category = category;
  }

  public String getType() {
    return this.type;
  }

  void setType(String type) {
    this.type = type;
  }

  public String getCountryCreatedIn() {
    return this.countryCreatedIn;
  }

  void setCountryCreatedIn(String countryCreatedIn) {
    this.countryCreatedIn = countryCreatedIn;
  }

  public int getYearOfCreation() {
    return this.yearOfCreation;
  }

  void setYearOfCreation(int yearOfCreation) {
    this.yearOfCreation = yearOfCreation;
  }

  public int getYearPublished() {
    return this.yearPublished;
  }

  void setYearPublished(int yearPublished) {
    this.yearPublished = yearPublished;
  }

  public int getAuthorYearOfBirth() {
    return this.authorYearOfBirth;
  }

  void setAuthorYearOfBirth(int authorYearOfBirth) {
    this.authorYearOfBirth = authorYearOfBirth;
  }

  public int getAuthorYearOfDeath() {
    return this.authorYearOfDeath;
  }

  void setAuthorYearOfDeath(int authorYearOfDeath) {
    this.authorYearOfDeath = authorYearOfDeath;
  }

  public String getAuthorCountry() {
    return this.authorCountry;
  }

  void setAuthorCountry(String authorCountry) {
    this.authorCountry = authorCountry;
  }

}
