/**
 * MongoDB entity representing the PDC record structure in storage.
 * TODO contemplate applying TDA (tell don't ask) principle to getter methods.
 */
package de.ddb.pdc.storage;

import java.util.List;
import org.springframework.data.annotation.Id;

public class MongoDataModel {

  @Id
  private String id;

  private final String itemId;
  private final String itemCategory;
  private final String institute;
  private final boolean publicDomain;
  private final List trace;
  private final String timestamp;

  /**
   * 
   * @param itemId
   * @param itemCategory
   * @param institute
   * @param publicDomain
   * @param trace
   * @param timestamp 
   */
  public MongoDataModel(String itemId, String itemCategory, String institute,
          boolean publicDomain, List trace, String timestamp) {

    this.itemId = itemId;
    this.itemCategory = itemCategory;
    this.institute = institute;
    this.publicDomain = publicDomain;
    this.trace = trace;
    this.timestamp = timestamp;
  }

  /**
   * 
   * @return 
   */
  public String getItemId() {
    return itemId;
  }

  /**
   * 
   * @return 
   */
  public String getItemCategory() {
    return itemCategory;
  }

  /**
   * 
   * @return 
   */
  public String getInstitute() {
    return institute;
  }

  /**
   * 
   * @return 
   */
  public boolean isPublicDomain() {
    return publicDomain;
  }

  /**
   * 
   * @return 
   */
  public List getTrace() {
    return trace;
  }

  /**
   * 
   * @return 
   */
  public String getTimestampAsString() {
    return timestamp;
  }

  /**
   * TODO add toString for trace
   *
   * @return custom record details
   */
  @Override
  public String toString() {
    return String.format(
            "Record[id=%s, itemId=%s, itemCategory=%s, institute=%s, "
            + "publicDomain=%b, timestamp=%s]",
            id, itemId, itemCategory, institute, publicDomain, timestamp
    );
  }

}
