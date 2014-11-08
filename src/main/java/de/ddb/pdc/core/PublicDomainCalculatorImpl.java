package de.ddb.pdc.core;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of a {@link PublicDomainCalculator}
 */
public class PublicDomainCalculatorImpl implements PublicDomainCalculator {

  /**
   * The country specific flow chart.
   */
  private final FlowChartState flowchart;

  /**
   * Create a new Public Domain Calculator that decides the public domain
   * problem based on the flow chart given.
   *
   * @param flowchart The country specific flow chart.
   */
  public PublicDomainCalculatorImpl(final FlowChartState flowchart) {
    this.flowchart = flowchart;
  }

  /**
   * @{inheritDoc}
   */
  @Override
  public Set<Category> getSupportedCategories() {
    final Set<Category> supportedCategories = new HashSet<Category>();
    for (final Category category : Category.values()) {
      try {
        this.flowchart.getInitialState(category);
        supportedCategories.add(category);
      } catch (final UnsupportedCategoryException e) {
        // an unsupported category will not be added to the set of supported
        // categories
      }
    }
    return supportedCategories;
  }

  /**
   * @{inheritDoc}
   */
  @Override
  public Questionnaire startQuestionnaire(final Category category)
      throws UnsupportedCategoryException {
    return new Questionnaire(this.flowchart.getInitialState(category));
  }

}
