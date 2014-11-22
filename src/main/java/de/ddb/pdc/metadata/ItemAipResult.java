package de.ddb.pdc.metadata;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat
@JsonIgnoreProperties(ignoreUnknown = true)
class ItemAipResult {

  @JsonProperty
  private EDMItem edm;

  public RDFItem getRDFItem() {
    return edm.getRdf();
  }
}
