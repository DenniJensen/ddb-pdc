package de.ddb.pdc.core;

import org.springframework.stereotype.Service;

/**
 * Factory for creating country-specific {@link PublicDomainCalculator} objects.
 * 
 * The factory's decision for a country-specific public domain calculator is
 * based on the two letter country-code described in ISO 3166-1 alpha-2.
 */
@Service
public class PublicDomainCalculatorFactory {
  public static PublicDomainCalculator getCalculator(String country)
      throws UnsupportedCountryException {
    switch (country) {
      case "de":
        return new PublicDomainCalculatorImplementation(
            FlowChartGermany.INITIAL);
      default:
        throw new UnsupportedCountryException(country);
    }
  }
}
