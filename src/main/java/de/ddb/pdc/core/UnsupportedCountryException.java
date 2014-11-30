package de.ddb.pdc.core;

/**
 * This exception is thrown by the {@link PublicDomainCalculatorFactory} if
 * there is no known public domain calculator implementation available for the
 * requested country or the country code was invalid.
 */
public class UnsupportedCountryException extends RuntimeException {

  private static final long serialVersionUID = 5007774657090621544L;

  /**
   * Creates a new unsupported country exception. The unsupported country has to
   * be passed as argument to the constructor.
   *
   * @param country The country that was not supported.
   */
  @SuppressWarnings("nls")
  public UnsupportedCountryException(String country) {
    super("Unsupported country code " + country);
  }

}
