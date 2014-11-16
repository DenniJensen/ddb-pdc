package de.ddb.pdc.metadata;

import java.util.LinkedHashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RDFItem {
  @JsonProperty("ProvidedCHO")
  private LinkedHashMap<String,Object> providedCHO;
  
  @JsonProperty("Agent")
  private Object agent;
  
  @JsonProperty("Aggregation")
  private LinkedHashMap<String,Object> aggregation;
  
  public LinkedHashMap<String,Object> getAggregation() {
    return aggregation;
  }

  public Object getAgent() {
    return agent;
  }

  public LinkedHashMap<String,Object> getProvidedCHO() {
    return providedCHO;
  }
  
}
