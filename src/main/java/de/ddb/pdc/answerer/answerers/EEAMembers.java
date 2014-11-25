package de.ddb.pdc.answerer.answerers;

/**
 * {@inheritDoc}
 */
@SuppressWarnings("javadoc")
public enum EEAMembers {
  AUSTRIA,
  BELGIUM,
  BULGARIA,
  CYPRUS,
  CZECH_REPUBLIC,
  DENMARK,
  ESTONIA,
  FINLAND,
  FRANCE,
  GERMANY,
  GREECE,
  HUNGARY,
  ICELAND,
  IRELAND,
  ITALY,
  LATVIA,
  LIECHTENSTEIN,
  LITHUANIA,
  LUXEMBOURG,
  MALTA,
  NETHERLANDS,
  NORWAY,
  POLAND,
  PORTUGAL,
  ROMANIA,
  SLOVAKIA,
  SLOVENIA,
  SPAIN,
  SWEDEN,
  UNITED_KINGDOM;

  /**
   * Determine whether the provided country is a member of the EEA.
   * @param country
   * @return
   */
  public static boolean isMember(String country) {
    if (country == null) {
      return false;
    }
    country = country.replaceAll("\\s+", "_"); //$NON-NLS-1$ //$NON-NLS-2$
    country = country.toUpperCase();
    try {
      EEAMembers.valueOf(country);
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }
}
