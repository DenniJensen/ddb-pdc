package de.ddb.pdc.metadata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * class for the edm item of the aip json result
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EDMItem {
  @JsonProperty("RDF")
  private RDFItem rdf;

  /**
   * @return the rdf of the aip json result
   */
  public RDFItem getRdf() {
    return rdf;
  }
}
