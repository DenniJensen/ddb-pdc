package de.ddb.pdc.answerer.answerers;

/**
 * {@inheritDoc}
 */
@SuppressWarnings("javadoc")
public enum EEAMembers {
  austria,
  belgium,
  bulgaria,
  cyprus,
  czech_republic,
  denmark,
  estonia,
  finland,
  france,
  germany,
  greece,
  hungary,
  iceland,
  ireland,
  italy,
  latvia,
  liechtenstein,
  lithuania,
  luxembourg,
  malta,
  netherlands,
  norway,
  poland,
  portugal,
  romania,
  slovakia,
  slovenia,
  spain,
  sweden,
  united_kingdom;

  /**
   * Determine whether the provided country is a member of the EEA.
   * @param country
   * @return
   */
  public static boolean isMember(String country) {
    country = country.replaceAll("\\s+", "_"); //$NON-NLS-1$ //$NON-NLS-2$
    country = country.toLowerCase();
    try {
      EEAMembers.valueOf(country);
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }
}
