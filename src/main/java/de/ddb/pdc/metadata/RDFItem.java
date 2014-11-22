package de.ddb.pdc.metadata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
class RDFItem {

  @JsonProperty("ProvidedCHO")
  private Map<String, Object> providedCHO;
  
  @JsonProperty("Agent")
  private Object agents;
  
  @JsonProperty("Aggregation")
  private Map<String, Object> aggregation;

  public int getPublishYear() {
    try {
      String publishedYear = (String) providedCHO.get("issued");
      if (publishedYear == null) {
        // The item is missing the publishing date.
        return -1;
      }
      return Integer.parseInt(publishedYear.split(",")[0]);
    } catch (NumberFormatException e) {
      throw new RuntimeException(e);
    }
  }

  public String getInstitution() {
    return (String) aggregation.get("provider");
  }

  public List<String> getAuthorIds() {
    List<String> authorIds = new ArrayList<>();
    for (Map agent : getAgents()) {
      String about = agent.get("@about").toString();
      if (about.startsWith("http://d-nb.info/")) {
        authorIds.add(about);
      }
    }
    return authorIds;
  }

  private List<Map> getAgents() {
    if (agents instanceof List) {
      // Multiple agents
      return (List) agents;
    } else {
      // A single agent
      List<Map> agentList = new ArrayList<>();
      agentList.add((Map) agents);
      return agentList;
    }
  }
}
