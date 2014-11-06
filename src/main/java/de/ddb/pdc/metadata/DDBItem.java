package de.ddb.pdc.metadata;

/**
 * Aggregates the metadata of an item from the DDB.
 */
public class DDBItem {
  private String id;
  private String author;
  private String title;
  private String subtitle;
  private String imgUrl;
  
  public DDBItem(String id) {
	  this.id = id;
  }

  public String getId() {
	return id;
  }

  public void setId(String id) {
	this.id = id;
  }

  public String getAuthor() {
	return author;
  }

  public void setAuthor(String author) {
	this.author = author;
  }

  public String getTitle() {
	return title;
  }

  public void setTitle(String title) {
	this.title = title;
  }

  public String getSubtitle() {
	return subtitle;
  }

  public void setSubtitle(String subtitle) {
	this.subtitle = subtitle;
  }

  public String getImgUrl() {
	return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
	this.imgUrl = imgUrl;
  }
  
}
