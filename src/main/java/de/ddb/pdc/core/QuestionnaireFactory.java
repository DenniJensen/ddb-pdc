package de.ddb.pdc.core;

import de.ddb.pdc.metadata.DDBItem;
import org.springframework.stereotype.Service;

/**
 * Provides {@link Questionnaire} objects to {@link PublicDomainCalculator}.
 */
@Service
public class QuestionnaireFactory {

  /**
   * Creates and returns a new {@link Questionnaire} for determining if
   * an item with the passed country of origin and category is public domain.
   *
   * @param country item's country of origin as country code (e.g. "de")
   * @param category item category
   * @param metadata additional information of the item
   * @return matching questionnaire
   */
  public Questionnaire build(String country, Category category, 
      DDBItem metadata)
      throws UnsupportedCountryException, UnsupportedCategoryException {
    
    FlowChartState flowchart = getFlowchartForCountry(country);
    FlowChartState initial = getInitialStateForCategory(flowchart, category);
    return new Questionnaire(initial, metadata);
  }

  private FlowChartState getFlowchartForCountry(String country) {
    switch (country) {
      case "de":
        return FlowChartStateGermany.INITIAL;
      default:
        throw new UnsupportedCountryException(country);
    }
  }

  private FlowChartState getInitialStateForCategory(
      FlowChartState flowchart, Category category) {
    return flowchart.getInitialState(category);
  }
}
