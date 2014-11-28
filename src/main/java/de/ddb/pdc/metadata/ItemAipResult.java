package de.ddb.pdc.metadata;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * call for the aip json result
 */
@JsonFormat
@JsonIgnoreProperties(ignoreUnknown = true)
class ItemAipResult {

  @JsonProperty
  private EDMItem edm;

  /**
   * @return rdf item of the json result or null
   */
  public RDFItem getRDFItem() {
    if (edm == null) {
      return null;
    }
    return edm.getRdf();
  }
}
