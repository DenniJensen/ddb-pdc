package de.ddb.pdc.core;

import de.ddb.pdc.metadata.DDBItem;

/**
 * Calculates the license status of DDB items.
 */
public interface PublicDomainCalculator {

  /**
   * Get the public domain status for the DDBItem that was passed as argument.
   * The public domain status is either true or false. true indicates that the
   * cultural good is in the public domain for the given country. The result
   * will also contain a list of questions that were asked to get the result
   * and the answers that were given to them.
   *
   * @param country The country to calculate the public domain status for.
   *        This has to be the 2 letter country code defined in
   *        ISO-3166-1 alpha-2.
   * @param metadata The DDBItem with the meta data about the cultural good.
   * @return A PDCResult object containing the public domain status and the
   *         questions and answer trace.
   */
  public PDCResult calculate(String country, DDBItem metadata);
}
