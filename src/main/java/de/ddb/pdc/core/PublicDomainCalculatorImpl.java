package de.ddb.pdc.core;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of a {@link PublicDomainCalculator}
 * 
 * @author Frank Zechert
 */
public class PublicDomainCalculatorImplementation implements
    PublicDomainCalculator {

  /**
   * The country specific flow chart.
   */
  private FlowChart flowchart;

  /**
   * Create a new Public Domain Calculator that decides the public domain
   * problem based on the flow chart given.
   * 
   * @param flowchart The country specific flow chart.
   */
  public PublicDomainCalculatorImplementation(FlowChart flowchart) {
    this.flowchart = flowchart;
  }

  /**
   * @{inheritDoc
   */
  @Override
  public Set<Category> getSupportedCategories() {
    Set<Category> supportedCategories = new HashSet<Category>();
    for (Category category : Category.values()) {
      try {
        this.flowchart.getInitialState(category);
        supportedCategories.add(category);
      }
      catch (UnsupportedCategoryException e) {
        // an unsupported category will not be added to the set of supported
        // categories
      }
    }
    return supportedCategories;
  }

  /**
   * @{inheritDoc
   */
  @Override
  public Questionnaire startQuestionnaire(Category category)
      throws UnsupportedCategoryException {
    return new Questionnaire(this.flowchart.getInitialState(category));
  }

}
