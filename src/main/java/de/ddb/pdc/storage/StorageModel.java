/**
 * Entity representing the PDC record structure in storage.
 * TODO apply TDA (tell don't ask) principle to getter methods.
 */
package de.ddb.pdc.storage;

import de.ddb.pdc.core.AnsweredQuestion;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.springframework.data.annotation.Id;

public class StorageModel {

  @Id
  private String id;

  private final String itemId;
  private final String itemCategory;
  private final String institute;
  private final boolean publicDomain;
  private final List<AnsweredQuestion> trace;
  private final String timestamp;

  /**
   * Constructor for storing new records.
   * The timestamp is automatically assigned to the current date and time.
   *
   * TODO move the SimpleDateFormat initialization elsewhere
   * TODO set the time zone through the properties file
   *
   * @param itemId
   * @param itemCategory
   * @param institute
   * @param publicDomain
   * @param trace
   */
  public StorageModel(String itemId, String itemCategory, String institute,
          boolean publicDomain, List<AnsweredQuestion> trace) {

    this.itemId = itemId;
    this.itemCategory = itemCategory;
    this.institute = institute;
    this.publicDomain = publicDomain;
    this.trace = trace;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    this.timestamp = sdf.format(new Date());
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
  public List<AnsweredQuestion> getTrace() {
    return trace;
  }

  /**
   *
   * @return
   */
  public String getTimestampAsString() {
    return timestamp;
  }

}
