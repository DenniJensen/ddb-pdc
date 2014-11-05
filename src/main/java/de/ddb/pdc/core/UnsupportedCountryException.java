package de.ddb.pdc.core;

/**
 * This exception is thrown by the {@link PublicDomainCalculatorFactory} if
 * there is no known public domain calculator implementation available for the
 * requested country or the country code was invalid.
 * 
 * @author Frank Zechert
 */
public class UnsupportedCountryException extends Exception {

  private static final long serialVersionUID = 5007774657090621544L;

  public UnsupportedCountryException(String country) {
    super(String.format("The country with the ISO 3166-1 alpha 2 code %s "
        + "is not supported."));
  }

}
