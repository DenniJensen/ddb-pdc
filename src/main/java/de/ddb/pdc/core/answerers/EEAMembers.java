package de.ddb.pdc.core.answerers;

/**
 * {@inheritDoc}
 */
@SuppressWarnings("javadoc")
public enum EEAMembers {
  AUSTRIA("AT"),
  BELGIUM("BE"),
  BULGARIA("BG"),
  CYPRUS("CY"),
  CZECH_REPUBLIC("CZ"),
  DENMARK("DK"),
  ESTONIA("EE"),
  FINLAND("FI"),
  FRANCE("FR"),
  GERMANY("DE"),
  GREECE("GR"),
  HUNGARY("HU"),
  ICELAND("IS"),
  IRELAND("IE"),
  ITALY("IT"),
  LATVIA("LV"),
  LIECHTENSTEIN("LI"),
  LITHUANIA("LT"),
  LUXEMBOURG("LU"),
  MALTA("MT"),
  NETHERLANDS("NL"),
  NORWAY("NO"),
  POLAND("PL"),
  PORTUGAL("PT"),
  ROMANIA("RO"),
  SLOVAKIA("SK"),
  SLOVENIA("SI"),
  SPAIN("ES"),
  SWEDEN("SE"),
  UNITED_KINGDOM("GB");

  private final String isoCode;
  
  private EEAMembers(String code) {
    this.isoCode = code;
  }

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
      
      for (EEAMembers member : EEAMembers.values()) {
        if (member.isoCode.toUpperCase().equals(country.toUpperCase())) {
          return true;
        }
      }
    }
    
    return false;
  }
}
