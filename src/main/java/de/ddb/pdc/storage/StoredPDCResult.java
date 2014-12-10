package de.ddb.pdc.storage;

import de.ddb.pdc.core.AnsweredQuestion;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.springframework.data.annotation.Id;

/**
 * Entity representing the PDC record structure in storage.
 *  
 */
public class StoredPDCResult {

  @Id
  private String id;

  private final String itemId;
  private final String itemCategory;
  private final String institution;
  private final boolean publicDomain;
  private final List<AnsweredQuestion> trace;
  private final Date createdDate;

  /**
   * Constructor for storing new records.
   * createdDate is set to the current date and time.
   */
  public StoredPDCResult(String itemId, String itemCategory, String institution,
          boolean publicDomain, List<AnsweredQuestion> trace) {

    this.itemId = itemId;
    this.itemCategory = itemCategory;
    this.institution = institution;
    this.publicDomain = publicDomain;
    this.trace = trace;
    this.createdDate = new Date();
  }

  public String getItemId() {
    return itemId;
  }

  public String getItemCategory() {
    return itemCategory;
  }

  public String getInstitution() {
    return institution;
  }

  public boolean isPublicDomain() {
    return publicDomain;
  }

  public List<AnsweredQuestion> getTrace() {
    return trace;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

}
