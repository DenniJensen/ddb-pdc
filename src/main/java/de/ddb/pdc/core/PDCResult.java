package de.ddb.pdc.core;

import de.ddb.pdc.metadata.DDBItem;
import de.ddb.pdc.storage.StoredPDCResult;
import java.util.Date;
import java.util.List;

/**
 * Represents the result of a public-domain calculation for a specific item.
 */
public class PDCResult {

  private final String itemId;
  private final String title;
  private final String subtitle;
  private final String imageUrl;
  private final Boolean publicDomain;
  private final List<AnsweredQuestion> trace;
  private final String itemCategory;
  private final String institution;
  private final Date createdDate;

  /**
   * Creates a PDCResult.
   *
   * @param publicDomain whether the item is considered public-domain
   * @param trace trace of calculation questions and answers
   * @param metadata properties of the item
   */
  public PDCResult(Boolean publicDomain, List<AnsweredQuestion> trace,
      DDBItem metadata) {

    this.itemId = metadata.getId();
    this.title = metadata.getTitle();
    this.subtitle = metadata.getSubtitle();
    this.imageUrl = metadata.getImageUrl();
    this.publicDomain = publicDomain;
    this.trace = trace;
    this.itemCategory = metadata.getCategory();
    this.institution = metadata.getInstitution();
    this.createdDate = new Date();
  }

  /**
   * Alternative constructor used to create an instance of @{link PDCResult}
   * from a @{link StoredPDCResult}.
   *
   * @param storedPDCResult record fetched from storage
   */
  public PDCResult(StoredPDCResult storedPDCResult) {
    this.itemId = storedPDCResult.getItemId();
    this.title = storedPDCResult.getTitle();
    this.subtitle = storedPDCResult.getSubtitle();
    this.imageUrl = storedPDCResult.getImageUrl();
    this.publicDomain = storedPDCResult.isPublicDomain();
    this.trace = storedPDCResult.getTrace();
    this.itemCategory = storedPDCResult.getItemCategory();
    this.institution = storedPDCResult.getInstitution();
    this.createdDate = storedPDCResult.getCreatedDate();
  }

  /**
   * @return the unique id of the cultural good.
   */
  public String getItemId() {
    return this.itemId;
  }

  /**
   * @return the DDB portal URL of the item.
   */
  public String getItemUrl() {
    return "https://www.deutsche-digitale-bibliothek.de/item/" + this.itemId;
  }

  /**
   * @return the title of the cultural good.
   */
  public String getTitle() {
    return this.title;
  }
  
  /**
   * @return the subtitle of the cultural good.
   */
  public String getSubtitle() {
    return this.subtitle;
  }
  
  /**
   * @return the URL of the image of the cultural good.
   */
  public String getImageUrl() {
    return this.imageUrl;
  }
  
  /**
   * Returns true if the item in question is considered public-domain by the
   * calculator, or false if not. Returns null if the status could not be
   * determined.
   *
   * @return true or false if the item is or is not in the public domain. null
   * if the decision could not be made.
   */
  public Boolean isPublicDomain() {
    return publicDomain;
  }

  /**
   * Returns all questions and answers that led to the result of the
   * calculation. The questions are returned in the order they were asked.
   *
   * @return A list of all asked questions and the given answer.
   */
  public List<AnsweredQuestion> getTrace() {
    return trace;
  }

  /**
   * @return the item category originating from @{link DDBItem}.
   */
  public String getItemCategory() {
    return this.itemCategory;
  }

  /**
   * @return the institution's name originating from @{link DDBItem}.
   */
  public String getInstitution() {
    return this.institution;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

}
