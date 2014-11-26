package de.ddb.pdc.core;

import static de.ddb.pdc.core.Category.BROADCAST;
import static de.ddb.pdc.core.Category.FIRST_FIXATION_OF_A_FILM;
import static de.ddb.pdc.core.Category.LITERARY_OR_ARTISTIC_WORK;
import static de.ddb.pdc.core.Category.NON_ORIGINAL_PHOTOGRAPH;
import static de.ddb.pdc.core.Category.PERFORMANCE;
import static de.ddb.pdc.core.Category.PHONOGRAM;
import static de.ddb.pdc.core.Category.SCIENTIFIC_EDITION_OF_AN_OUT_OF_COPYRIGHT_WORK;
import static de.ddb.pdc.core.Category.UNORIGINAL_DATABASE;
import static de.ddb.pdc.core.Question.ACT_OF_PARLIAMENT;
import static de.ddb.pdc.core.Question.ANNOUNCEMENT_BY_AUTHORITY;
import static de.ddb.pdc.core.Question.AUTHOR_ANONYMOUS;
import static de.ddb.pdc.core.Question.AUTHOR_DIED_MORE_THAN_70_YEARS_AGO;
import static de.ddb.pdc.core.Question.AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA;
import static de.ddb.pdc.core.Question.AUTHOR_FROM_TRIPTIS;
import static de.ddb.pdc.core.Question.AUTHOR_NATURAL_PERSON;
import static de.ddb.pdc.core.Question.CHANGED_OR_COMPLETED_WITHIN_LAST_15_YEARS;
import static de.ddb.pdc.core.Question.COUNTRY_OF_ORIGIN_EEA;
import static de.ddb.pdc.core.Question.COUNTRY_OF_ORIGIN_TRIPTIS;
import static de.ddb.pdc.core.Question.COUNTRY_REGISTERED_OFFICE_EEA;
import static de.ddb.pdc.core.Question.COURT_DECISION_OR_DECISION_FORMULA;
import static de.ddb.pdc.core.Question.CREATED_MORE_THAN_25_YEARS_AGO;
import static de.ddb.pdc.core.Question.CREATED_MORE_THAN_50_YEARS_AGO;
import static de.ddb.pdc.core.Question.CREATED_MORE_THAN_70_YEARS_AGO;
import static de.ddb.pdc.core.Question.FIRST_BROADCAST_25_YEARS;
import static de.ddb.pdc.core.Question.FIXATION_PUBLISHED_50_YEARS_FROM_PERFORMANCE;
import static de.ddb.pdc.core.Question.FIXATION_PUBLISHED_MORE_THAN_50_YEARS_AGO;
import static de.ddb.pdc.core.Question.FORMED_UNDER_LAW_OF_EEA;
import static de.ddb.pdc.core.Question.GOVERNMENT_DIRECTIVE;
import static de.ddb.pdc.core.Question.OFFICIAL_WORK_INTENDED_TO_BE_GENERALLY_RECEIVED;
import static de.ddb.pdc.core.Question.OPERATION_LINKED_TO_ECONOMY_EEA;
import static de.ddb.pdc.core.Question.PERFORMED_MORE_THAN_50_YEARS_AGO;
import static de.ddb.pdc.core.Question.PUBLISHED_AFTER_COMPLETION_OR_CHANGE;
import static de.ddb.pdc.core.Question.PUBLISHED_IN_LAST_15_YEARS;
import static de.ddb.pdc.core.Question.PUBLISHED_MORE_THAN_25_YEARS_AGO;
import static de.ddb.pdc.core.Question.PUBLISHED_MORE_THAN_50_YEARS_AGO;
import static de.ddb.pdc.core.Question.PUBLISHED_MORE_THAN_70_YEARS_AGO;
import static de.ddb.pdc.core.Question.PUBLISHED_MORE_THAN_70_YEAR_AFTER_CREATION;
import static de.ddb.pdc.core.Question.PUBLISHED_WITHIN_50_YEAR_AFTER_CREATION;
import static de.ddb.pdc.core.Question.PUBLISHED_WITHIN_70_YEARS_OF_DEATH;
import static de.ddb.pdc.core.Question.REGISTERED_IN_EEA;
import static de.ddb.pdc.core.Question.RIGHTSHOLDER_RESIDENT_EEA;
import static de.ddb.pdc.core.Question.RIGHT_HOLDER_COMPANY_OR_FIRM;
import static de.ddb.pdc.core.Question.WORK_PUBLISHED_OR_COMMUNICATED;

import org.junit.Before;
import org.junit.Test;

public class PublicDomainCalculatorFactoryTest {
  private PublicDomainCalculator pdcDE;

  @Before
  public void setupPCDde() throws UnsupportedCountryException {
    pdcDE= new PublicDomainCalculatorFactory().getCalculator("de");
  }

  /**
   * The following are tests of all possible paths in the german PDC graph, encoded by
   * listing all Questions that are answered with a YES in the order in which they are asked.
   * (Since there are only YES/NO answers, this is enough to encode a unique path.)
   *
   * All paths are taken from http://outofcopyright.eu/research/Germany.pdf, with the
   * leftmost path as the first, and the rightmost path as the last test.
   *
   * The actual testing takes place in QuestionnaireTestCase, where all answers are
   * given as specified here, and the result is compared with the expectedResult.
   */

  @Test
  public void testPDCdeLiteraryOrArtistic() {
    Question[] yesQuestions = new Question[]{OFFICIAL_WORK_INTENDED_TO_BE_GENERALLY_RECEIVED};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

    yesQuestions = new Question[]{COURT_DECISION_OR_DECISION_FORMULA};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

    yesQuestions = new Question[]{ACT_OF_PARLIAMENT};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

    yesQuestions = new Question[]{GOVERNMENT_DIRECTIVE};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

    yesQuestions = new Question[]{ANNOUNCEMENT_BY_AUTHORITY};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

    yesQuestions = new Question[]{AUTHOR_NATURAL_PERSON, AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA, AUTHOR_DIED_MORE_THAN_70_YEARS_AGO};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

    yesQuestions = new Question[]{AUTHOR_NATURAL_PERSON, AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();

    yesQuestions = new Question[]{AUTHOR_NATURAL_PERSON, AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA, WORK_PUBLISHED_OR_COMMUNICATED, PUBLISHED_WITHIN_70_YEARS_OF_DEATH, AUTHOR_DIED_MORE_THAN_70_YEARS_AGO};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

    yesQuestions = new Question[]{AUTHOR_NATURAL_PERSON, AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA, WORK_PUBLISHED_OR_COMMUNICATED, PUBLISHED_WITHIN_70_YEARS_OF_DEATH};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();

    yesQuestions = new Question[]{AUTHOR_NATURAL_PERSON, AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA, WORK_PUBLISHED_OR_COMMUNICATED, PUBLISHED_MORE_THAN_25_YEARS_AGO};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

    yesQuestions = new Question[]{AUTHOR_NATURAL_PERSON, AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA, WORK_PUBLISHED_OR_COMMUNICATED};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();

    yesQuestions = new Question[]{AUTHOR_NATURAL_PERSON, COUNTRY_OF_ORIGIN_EEA, AUTHOR_DIED_MORE_THAN_70_YEARS_AGO};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

    yesQuestions = new Question[]{AUTHOR_NATURAL_PERSON, COUNTRY_OF_ORIGIN_EEA};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();

    yesQuestions = new Question[]{AUTHOR_NATURAL_PERSON, COUNTRY_OF_ORIGIN_EEA, WORK_PUBLISHED_OR_COMMUNICATED, PUBLISHED_WITHIN_70_YEARS_OF_DEATH, AUTHOR_DIED_MORE_THAN_70_YEARS_AGO};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

    yesQuestions = new Question[]{AUTHOR_NATURAL_PERSON, COUNTRY_OF_ORIGIN_EEA, WORK_PUBLISHED_OR_COMMUNICATED, PUBLISHED_WITHIN_70_YEARS_OF_DEATH};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();

    yesQuestions = new Question[]{AUTHOR_NATURAL_PERSON, COUNTRY_OF_ORIGIN_EEA, WORK_PUBLISHED_OR_COMMUNICATED, PUBLISHED_MORE_THAN_25_YEARS_AGO};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

    yesQuestions = new Question[]{AUTHOR_NATURAL_PERSON, COUNTRY_OF_ORIGIN_EEA, WORK_PUBLISHED_OR_COMMUNICATED};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();

    yesQuestions = new Question[]{AUTHOR_NATURAL_PERSON};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

    yesQuestions = new Question[]{AUTHOR_NATURAL_PERSON, AUTHOR_FROM_TRIPTIS};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, null).test();

    yesQuestions = new Question[]{AUTHOR_NATURAL_PERSON, COUNTRY_OF_ORIGIN_TRIPTIS};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, null).test();

    yesQuestions = new Question[]{AUTHOR_ANONYMOUS, COUNTRY_OF_ORIGIN_TRIPTIS};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, null).test();

    yesQuestions = new Question[]{AUTHOR_ANONYMOUS, AUTHOR_FROM_TRIPTIS};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, null).test();

    yesQuestions = new Question[]{AUTHOR_ANONYMOUS};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

    yesQuestions = new Question[]{AUTHOR_ANONYMOUS, COUNTRY_OF_ORIGIN_EEA, CREATED_MORE_THAN_70_YEARS_AGO};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

    yesQuestions = new Question[]{AUTHOR_ANONYMOUS, COUNTRY_OF_ORIGIN_EEA};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();

    yesQuestions = new Question[]{AUTHOR_ANONYMOUS, COUNTRY_OF_ORIGIN_EEA, WORK_PUBLISHED_OR_COMMUNICATED, PUBLISHED_MORE_THAN_70_YEAR_AFTER_CREATION, PUBLISHED_MORE_THAN_25_YEARS_AGO};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

    yesQuestions = new Question[]{AUTHOR_ANONYMOUS, COUNTRY_OF_ORIGIN_EEA, WORK_PUBLISHED_OR_COMMUNICATED, PUBLISHED_MORE_THAN_70_YEAR_AFTER_CREATION};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();

    yesQuestions = new Question[]{AUTHOR_ANONYMOUS, COUNTRY_OF_ORIGIN_EEA, WORK_PUBLISHED_OR_COMMUNICATED, PUBLISHED_MORE_THAN_70_YEARS_AGO};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

    yesQuestions = new Question[]{AUTHOR_ANONYMOUS, COUNTRY_OF_ORIGIN_EEA, WORK_PUBLISHED_OR_COMMUNICATED};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();

    yesQuestions = new Question[]{AUTHOR_ANONYMOUS, AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA, CREATED_MORE_THAN_70_YEARS_AGO};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

    yesQuestions = new Question[]{AUTHOR_ANONYMOUS, AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();

    yesQuestions = new Question[]{AUTHOR_ANONYMOUS, AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA, WORK_PUBLISHED_OR_COMMUNICATED, PUBLISHED_MORE_THAN_70_YEAR_AFTER_CREATION, PUBLISHED_MORE_THAN_25_YEARS_AGO};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

    yesQuestions = new Question[]{AUTHOR_ANONYMOUS, AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA, WORK_PUBLISHED_OR_COMMUNICATED, PUBLISHED_MORE_THAN_70_YEAR_AFTER_CREATION};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();

    yesQuestions = new Question[]{AUTHOR_ANONYMOUS, AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA, WORK_PUBLISHED_OR_COMMUNICATED, PUBLISHED_MORE_THAN_70_YEARS_AGO};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

    yesQuestions = new Question[]{AUTHOR_ANONYMOUS, AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA, WORK_PUBLISHED_OR_COMMUNICATED};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();

    yesQuestions = new Question[]{};
    new QuestionnaireTestCase(pdcDE, LITERARY_OR_ARTISTIC_WORK, yesQuestions, null).test();
  }

  @Test
  public void testPDCdeNonOriginalPhotograph() {
    Question[] yesQuestions = new Question[]{WORK_PUBLISHED_OR_COMMUNICATED};
    new QuestionnaireTestCase(pdcDE, NON_ORIGINAL_PHOTOGRAPH, yesQuestions, false).test();

    yesQuestions = new Question[]{WORK_PUBLISHED_OR_COMMUNICATED, PUBLISHED_MORE_THAN_50_YEARS_AGO};
    new QuestionnaireTestCase(pdcDE, NON_ORIGINAL_PHOTOGRAPH, yesQuestions, true).test();

    yesQuestions = new Question[]{};
    new QuestionnaireTestCase(pdcDE, NON_ORIGINAL_PHOTOGRAPH, yesQuestions, false).test();

    yesQuestions = new Question[]{CREATED_MORE_THAN_50_YEARS_AGO};
    new QuestionnaireTestCase(pdcDE, NON_ORIGINAL_PHOTOGRAPH, yesQuestions, true).test();
  }

  @Test
  public void testPDCdeScientificEdition() {
    Question[] yesQuestions = new Question[]{WORK_PUBLISHED_OR_COMMUNICATED, PUBLISHED_MORE_THAN_25_YEARS_AGO};
    new QuestionnaireTestCase(pdcDE, SCIENTIFIC_EDITION_OF_AN_OUT_OF_COPYRIGHT_WORK, yesQuestions, true).test();

    yesQuestions = new Question[]{WORK_PUBLISHED_OR_COMMUNICATED};
    new QuestionnaireTestCase(pdcDE, SCIENTIFIC_EDITION_OF_AN_OUT_OF_COPYRIGHT_WORK, yesQuestions, false).test();

    yesQuestions = new Question[]{CREATED_MORE_THAN_25_YEARS_AGO};
    new QuestionnaireTestCase(pdcDE, SCIENTIFIC_EDITION_OF_AN_OUT_OF_COPYRIGHT_WORK, yesQuestions, true).test();

    yesQuestions = new Question[]{};
    new QuestionnaireTestCase(pdcDE, SCIENTIFIC_EDITION_OF_AN_OUT_OF_COPYRIGHT_WORK, yesQuestions, false).test();
  }

  @Test
  public void testPDCdePhonogram() {
    Question[] yesQuestions = new Question[]{};
    new QuestionnaireTestCase(pdcDE, PHONOGRAM, yesQuestions, false).test();

    yesQuestions = new Question[]{CREATED_MORE_THAN_50_YEARS_AGO};
    new QuestionnaireTestCase(pdcDE, PHONOGRAM, yesQuestions, true).test();

    yesQuestions = new Question[]{PUBLISHED_WITHIN_50_YEAR_AFTER_CREATION};
    new QuestionnaireTestCase(pdcDE, PHONOGRAM, yesQuestions, false).test();

    yesQuestions = new Question[]{PUBLISHED_WITHIN_50_YEAR_AFTER_CREATION, PUBLISHED_MORE_THAN_50_YEARS_AGO};
    new QuestionnaireTestCase(pdcDE, PHONOGRAM, yesQuestions, true).test();
  }

  @Test
  public void testPDCdeBroadcast() {
    Question[] yesQuestions = new Question[]{FIRST_BROADCAST_25_YEARS};
    new QuestionnaireTestCase(pdcDE, BROADCAST, yesQuestions, true).test();

    yesQuestions = new Question[]{};
    new QuestionnaireTestCase(pdcDE, BROADCAST, yesQuestions, false).test();
  }

  @Test
  public void testPDCdePerformance() {
    Question[] yesQuestions = new Question[]{PERFORMED_MORE_THAN_50_YEARS_AGO};
    new QuestionnaireTestCase(pdcDE, PERFORMANCE, yesQuestions, true).test();

    yesQuestions = new Question[]{};
    new QuestionnaireTestCase(pdcDE, PERFORMANCE, yesQuestions, false).test();

    yesQuestions = new Question[]{FIXATION_PUBLISHED_50_YEARS_FROM_PERFORMANCE, FIXATION_PUBLISHED_MORE_THAN_50_YEARS_AGO};
    new QuestionnaireTestCase(pdcDE, PERFORMANCE, yesQuestions, true).test();

    yesQuestions = new Question[]{FIXATION_PUBLISHED_50_YEARS_FROM_PERFORMANCE};
    new QuestionnaireTestCase(pdcDE, PERFORMANCE, yesQuestions, false).test();
  }

  @Test
  public void testPDCdeUnoriginalDatabase() {
    Question[] yesQuestions = new Question[]{RIGHT_HOLDER_COMPANY_OR_FIRM};
    new QuestionnaireTestCase(pdcDE, UNORIGINAL_DATABASE, yesQuestions, true).test();

    yesQuestions = new Question[]{RIGHT_HOLDER_COMPANY_OR_FIRM, COUNTRY_REGISTERED_OFFICE_EEA};
    new QuestionnaireTestCase(pdcDE, UNORIGINAL_DATABASE, yesQuestions, true).test();

    yesQuestions = new Question[]{RIGHT_HOLDER_COMPANY_OR_FIRM, COUNTRY_REGISTERED_OFFICE_EEA, OPERATION_LINKED_TO_ECONOMY_EEA, PUBLISHED_AFTER_COMPLETION_OR_CHANGE};
    new QuestionnaireTestCase(pdcDE, UNORIGINAL_DATABASE, yesQuestions, true).test();

    yesQuestions = new Question[]{RIGHT_HOLDER_COMPANY_OR_FIRM, COUNTRY_REGISTERED_OFFICE_EEA, OPERATION_LINKED_TO_ECONOMY_EEA, PUBLISHED_AFTER_COMPLETION_OR_CHANGE, PUBLISHED_IN_LAST_15_YEARS};
    new QuestionnaireTestCase(pdcDE, UNORIGINAL_DATABASE, yesQuestions, false).test();

    yesQuestions = new Question[]{RIGHT_HOLDER_COMPANY_OR_FIRM, COUNTRY_REGISTERED_OFFICE_EEA, OPERATION_LINKED_TO_ECONOMY_EEA};
    new QuestionnaireTestCase(pdcDE, UNORIGINAL_DATABASE, yesQuestions, true).test();

    yesQuestions = new Question[]{RIGHT_HOLDER_COMPANY_OR_FIRM, COUNTRY_REGISTERED_OFFICE_EEA, OPERATION_LINKED_TO_ECONOMY_EEA, CHANGED_OR_COMPLETED_WITHIN_LAST_15_YEARS};
    new QuestionnaireTestCase(pdcDE, UNORIGINAL_DATABASE, yesQuestions, false).test();

    yesQuestions = new Question[]{RIGHT_HOLDER_COMPANY_OR_FIRM, FORMED_UNDER_LAW_OF_EEA};
    new QuestionnaireTestCase(pdcDE, UNORIGINAL_DATABASE, yesQuestions, true).test();

    yesQuestions = new Question[]{RIGHT_HOLDER_COMPANY_OR_FIRM, FORMED_UNDER_LAW_OF_EEA, REGISTERED_IN_EEA, PUBLISHED_AFTER_COMPLETION_OR_CHANGE};
    new QuestionnaireTestCase(pdcDE, UNORIGINAL_DATABASE, yesQuestions, true).test();

    yesQuestions = new Question[]{RIGHT_HOLDER_COMPANY_OR_FIRM, FORMED_UNDER_LAW_OF_EEA, REGISTERED_IN_EEA, PUBLISHED_AFTER_COMPLETION_OR_CHANGE, PUBLISHED_IN_LAST_15_YEARS};
    new QuestionnaireTestCase(pdcDE, UNORIGINAL_DATABASE, yesQuestions, false).test();

    yesQuestions = new Question[]{RIGHT_HOLDER_COMPANY_OR_FIRM, FORMED_UNDER_LAW_OF_EEA, REGISTERED_IN_EEA};
    new QuestionnaireTestCase(pdcDE, UNORIGINAL_DATABASE, yesQuestions, true).test();

    yesQuestions = new Question[]{RIGHT_HOLDER_COMPANY_OR_FIRM, FORMED_UNDER_LAW_OF_EEA, REGISTERED_IN_EEA, CHANGED_OR_COMPLETED_WITHIN_LAST_15_YEARS};
    new QuestionnaireTestCase(pdcDE, UNORIGINAL_DATABASE, yesQuestions, false).test();

    yesQuestions = new Question[]{RIGHTSHOLDER_RESIDENT_EEA, PUBLISHED_AFTER_COMPLETION_OR_CHANGE};
    new QuestionnaireTestCase(pdcDE, UNORIGINAL_DATABASE, yesQuestions, true).test();

    yesQuestions = new Question[]{RIGHTSHOLDER_RESIDENT_EEA, PUBLISHED_AFTER_COMPLETION_OR_CHANGE, PUBLISHED_IN_LAST_15_YEARS};
    new QuestionnaireTestCase(pdcDE, UNORIGINAL_DATABASE, yesQuestions, false).test();

    yesQuestions = new Question[]{RIGHTSHOLDER_RESIDENT_EEA};
    new QuestionnaireTestCase(pdcDE, UNORIGINAL_DATABASE, yesQuestions, true).test();

    yesQuestions = new Question[]{RIGHTSHOLDER_RESIDENT_EEA, CHANGED_OR_COMPLETED_WITHIN_LAST_15_YEARS};
    new QuestionnaireTestCase(pdcDE, UNORIGINAL_DATABASE, yesQuestions, false).test();

    yesQuestions = new Question[]{};
    new QuestionnaireTestCase(pdcDE, UNORIGINAL_DATABASE, yesQuestions, true).test();
  }

  @Test
  public void testPDCdeFirstFixationFilm() {
    Question[] yesQuestions = new Question[]{};
    new QuestionnaireTestCase(pdcDE, FIRST_FIXATION_OF_A_FILM, yesQuestions, false).test();

    yesQuestions = new Question[]{CREATED_MORE_THAN_50_YEARS_AGO};
    new QuestionnaireTestCase(pdcDE, FIRST_FIXATION_OF_A_FILM, yesQuestions, true).test();

    yesQuestions = new Question[]{PUBLISHED_WITHIN_50_YEAR_AFTER_CREATION};
    new QuestionnaireTestCase(pdcDE, FIRST_FIXATION_OF_A_FILM, yesQuestions, false).test();

    yesQuestions = new Question[]{PUBLISHED_WITHIN_50_YEAR_AFTER_CREATION,PUBLISHED_MORE_THAN_50_YEARS_AGO};
    new QuestionnaireTestCase(pdcDE, FIRST_FIXATION_OF_A_FILM, yesQuestions, true).test();
  }
}
