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

  /**
   * Get the public domain calculator for the specified country.
   *
   * @param country The country to get the calculator for. This hast to be the
   *        ISO 3166-1 alpha 2 country code.
   * @return The public domain calculator for the specified country.
   * @throws UnsupportedCountryException The specified country is not supported.
   *         Either you specified a wrong country code or there is no
   *         implementation available for that countr.
   */
  public static PublicDomainCalculator getCalculator(String country)
      throws UnsupportedCountryException {
    switch (country) {
      case "de": //$NON-NLS-1$
        return new PublicDomainCalculatorImpl(FlowChartStateGermany.INITIAL);
      default:
        throw new UnsupportedCountryException(country);
    }
  }
}
