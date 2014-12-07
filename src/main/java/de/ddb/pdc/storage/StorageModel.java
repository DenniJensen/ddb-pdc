package de.ddb.pdc.storage;

import de.ddb.pdc.core.AnsweredQuestion;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.springframework.data.annotation.Id;

/**
 * Entity representing the PDC record structure in storage.
 */
public class StorageModel {

  @Id
  private String id;

  private final String itemId;
  private final String itemCategory;
  private final String institution;
  private final boolean publicDomain;
  private final List<AnsweredQuestion> trace;
  private final String timestamp;

  /**
   * Constructor for storing new records.
   * The timestamp is automatically assigned to the current date and time.
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
    this.institution = institute;
    this.publicDomain = publicDomain;
    this.trace = trace;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    this.timestamp = sdf.format(new Date());
  }

  public String getItemId() {
    return itemId;
  }

  public String getItemCategory() {
    return itemCategory;
  }

  public String getInstitute() {
    return institution;
  }

  public boolean isPublicDomain() {
    return publicDomain;
  }

  public List<AnsweredQuestion> getTrace() {
    return trace;
  }

  public String getTimestampAsString() {
    return timestamp;
  }

}
