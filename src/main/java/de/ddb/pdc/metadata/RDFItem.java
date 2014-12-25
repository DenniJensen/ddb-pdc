package de.ddb.pdc.metadata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
class RDFItem {

  @JsonProperty("ProvidedCHO")
  private Map<String, Object> providedCHO;
  
  @JsonProperty("Agent")
  private Object agents;
  
  @JsonProperty("Aggregation")
  private Map<String, Object> aggregation;

  /**
   * @return a 4 digit int if a issued year is found or -1
   */
  public int getPublishYear() {
    try {
      String publishedYear = (String) providedCHO.get("issued");
      if (publishedYear == null) {
        // The item is missing the publishing date.
        return -1;
      }

      return getDateAsInt(publishedYear,"\\d{4}");
    } catch (NumberFormatException e) {
      return -1;
    }
  }
  
  private int getDateAsInt(String date, String regex) {
    return Integer.parseInt(MetadataUtils.useRegex(date, regex));
  }

  /**
   * @return  institution from the dataProvider item of aggregation
   */
  public String getInstitution() {
    List<Object> dataProvider = (ArrayList<Object>) aggregation
        .get("dataProvider");

    if (dataProvider == null || dataProvider.size() == 0) {
      return null;
    }
    return dataProvider.get(0).toString();
  }

  /**
   * @return  list of all author ids
   */
  public List<String> getAuthorIds() {
    List<String> authorIds = new ArrayList<>();
    List<Map> agents = getAgents();
    if (agents != null) {
      for (Map agent : getAgents()) {
        String about = agent.get("@about").toString();
        if (about.startsWith("http://d-nb.info/")) {
          authorIds.add(about);
        }
      }
    }
    return authorIds;
  }

  /**
   * @return  list of all agents item in rdf item
   */
  private List<Map> getAgents() {
    if (agents == null) {
      return null;
    }
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
