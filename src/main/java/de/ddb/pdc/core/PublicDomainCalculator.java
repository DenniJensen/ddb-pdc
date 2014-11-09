package de.ddb.pdc.core;

import java.util.Set;

/**
 * Interface to the public domain calculator. The public domain calculator
 * decides whether some cultural good should belong to the public domain or not.
 *
 * The public domain calculator uses flow charts from EropeanaLabs
 * OutOfCopyright.eu project released under Creative Commons
 * Attribution-ShareAlike 3.0 Netherlands License to decide whether a cultural
 * good shoud fall into the public domain or not.
 *
 * It should be noted that multiple layers of rights (whether of copyright,
 * neighbouring or related rights or sui generis rights) might apply to the same
 * information product. Please make sure that you correctly identify and apply
 * the Public Domain Calculators to all subject matter that qualifies for
 * protection.
 *
 * Please note that the Outofcopyright Calculators reflect the state of the law
 * in the featured jurisdictions only up until May 2011. They do not reflect
 * subsequent changes in the law. In particular, they have not been updated to
 * take account of the amendments introduced to the EU's Directive 2006/116/EC
 * (Term Directive) by Directive 2011/77/EU (Term Extension Directive) and
 * corresponding changes to national rules by implementing legislation.
 *
 * The Public Domain Calculator is not intended to replace the case-by-case
 * assessment by a legal expert of the public domain status of a copyrighted
 * work or other protected subject matter. For legal certainty as to whether the
 * term of protection of copyright or related rights has expired please contact
 * a legal professional.
 *
 * The Public Domain Calculator is intended to provide the public domain status
 * of examined material exclusively in the selected jurisdiction. Where an EU
 * Member State has overseas or other special-status territories, the national
 * Calculators are applicable only to the extent that the territory is subject
 * to the Member State's copyright legislation.
 *
 * Please note that the public domain status of subject matter may differ
 * between jurisdictions. In relation to neighbouring or related rights (i.e.
 * rights over performances, phonograms, the first fixation of a film and
 * broadcast), the Public Domain Calculator only applies when at least one of
 * the right-holders is a national of an EEA state, with the exception of
 * Switzerland, where the Public Domain Calculator only applies when at least
 * one of the right-holders is a Swiss national. The same is true for films
 * where protection is sought in the UK, Cyprus and Ireland. The Public Domain
 * Calculator does not cover questions of authors’ or performers’ moral rights.
 */
public interface PublicDomainCalculator {

  /**
   * Get the supported categories. Returns the set of categories of cultural
   * good that are supported by this public domain calculator. This is a subset
   * of the Category enumeration.
   *
   * @return the set of supported categories
   */
  public Set<Category> getSupportedCategories();

  /**
   * Starts a questionnaire for the given category. This method starts a new
   * questionnaire for the classification of a cultural good that falls into the
   * given category. The category must be supported by this public domain
   * calculator. Otherwise an UnsupportedCategoryException will be thrown. A
   * questionnaire is a stateful object. Do not reuse the same questionnaire for
   * other classifications.
   *
   * @param category The category of cultural good to get the questionnaire for
   * @return The Questionnaire in its initial state to classify your cultural
   *         good
   * @throws UnsupportedCategoryException An unsupported category was given.
   */
  public Questionnaire startQuestionnaire(Category category)
      throws UnsupportedCategoryException;
}
