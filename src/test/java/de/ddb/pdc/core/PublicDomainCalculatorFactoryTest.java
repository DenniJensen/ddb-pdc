package de.ddb.pdc.core;

import org.junit.Test;
import static org.junit.Assert.*;
import static de.ddb.pdc.core.Category.*;
import static de.ddb.pdc.core.Question.*;

public class PublicDomainCalculatorFactoryTest {
    PublicDomainCalculator pdc;

    @Test
    public void testPDCde() {
        try {
            pdc = PublicDomainCalculatorFactory.getCalculator("de");
        } catch (UnsupportedCountryException e) {
            assertFalse("UnsupportedCountryException (de)",true);
        }

        /**
         * The following is a test of all possible paths in the german PDC graph, encoded by
         * listing all Questions that are answered with a YES in the order in which they are asked.
         * (Since there are only YES/NO answers, this is enough to encode a unique path.)
         *
         * All paths are taken from http://outofcopyright.eu/research/Germany.pdf, with the
         * leftmost path as the first, and the rightmost path as the last test.
         *
         * The actual testing takes place in QuestionnaireTestCase, where all answers are
         * given as specified here, and the result is compared with the expectedResult.
         */

        // Tests for LITERARY_OR_ARTISTIC_WORK
        Question[] yesQuestions=new Question[]{OFFICIAL_WORK_INTENDED_TO_BE_GENERALLY_RECEIVED};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

        yesQuestions=new Question[]{COURT_DECISION_OR_DECISION_FORMULA};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

        yesQuestions=new Question[]{ACT_OF_PARLIAMENT};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

        yesQuestions=new Question[]{GOVERNMENT_DIRECTIVE};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

        yesQuestions=new Question[]{ANNOUNCEMENT_BY_AUTHORITY};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

        yesQuestions=new Question[]{AUTHOR_NATURAL_PERSON,AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA,AUTHOR_DIED_MORE_THAN_70_YEARS_AGO};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

        yesQuestions=new Question[]{AUTHOR_NATURAL_PERSON,AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();

        yesQuestions=new Question[]{AUTHOR_NATURAL_PERSON,AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA,WORK_PUBLISHED_OR_COMMUNICATED,PUBLISHED_WITHIN_70_YEARS_OF_DEATH,AUTHOR_DIED_MORE_THAN_70_YEARS_AGO};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

        yesQuestions=new Question[]{AUTHOR_NATURAL_PERSON,AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA,WORK_PUBLISHED_OR_COMMUNICATED,PUBLISHED_WITHIN_70_YEARS_OF_DEATH};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();

        yesQuestions=new Question[]{AUTHOR_NATURAL_PERSON,AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA,WORK_PUBLISHED_OR_COMMUNICATED,PUBLISHED_MORE_THAN_25_YEARS_AGO};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

        yesQuestions=new Question[]{AUTHOR_NATURAL_PERSON,AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA,WORK_PUBLISHED_OR_COMMUNICATED};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();

        yesQuestions=new Question[]{AUTHOR_NATURAL_PERSON,COUNTRY_OF_ORIGIN_EEA,AUTHOR_DIED_MORE_THAN_70_YEARS_AGO};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

        yesQuestions=new Question[]{AUTHOR_NATURAL_PERSON,COUNTRY_OF_ORIGIN_EEA};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();

        yesQuestions=new Question[]{AUTHOR_NATURAL_PERSON,COUNTRY_OF_ORIGIN_EEA,WORK_PUBLISHED_OR_COMMUNICATED,PUBLISHED_WITHIN_70_YEARS_OF_DEATH,AUTHOR_DIED_MORE_THAN_70_YEARS_AGO};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

        yesQuestions=new Question[]{AUTHOR_NATURAL_PERSON,COUNTRY_OF_ORIGIN_EEA,WORK_PUBLISHED_OR_COMMUNICATED,PUBLISHED_WITHIN_70_YEARS_OF_DEATH};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();

        yesQuestions=new Question[]{AUTHOR_NATURAL_PERSON,COUNTRY_OF_ORIGIN_EEA,WORK_PUBLISHED_OR_COMMUNICATED,PUBLISHED_MORE_THAN_25_YEARS_AGO};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

        yesQuestions=new Question[]{AUTHOR_NATURAL_PERSON,COUNTRY_OF_ORIGIN_EEA,WORK_PUBLISHED_OR_COMMUNICATED};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();

        yesQuestions=new Question[]{AUTHOR_NATURAL_PERSON};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

        yesQuestions=new Question[]{AUTHOR_NATURAL_PERSON,AUTHOR_FROM_TRIPTIS};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();

        yesQuestions=new Question[]{AUTHOR_NATURAL_PERSON,COUNTRY_OF_ORIGIN_TRIPTIS};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();

        yesQuestions=new Question[]{AUTHOR_ANONYMOUS,COUNTRY_OF_ORIGIN_TRIPTIS};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();

        yesQuestions=new Question[]{AUTHOR_ANONYMOUS,AUTHOR_FROM_TRIPTIS};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();

        yesQuestions=new Question[]{AUTHOR_ANONYMOUS};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

        yesQuestions=new Question[]{AUTHOR_ANONYMOUS,COUNTRY_OF_ORIGIN_EEA,CREATED_MORE_THAN_70_YEARS_AGO};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

        yesQuestions=new Question[]{AUTHOR_ANONYMOUS,COUNTRY_OF_ORIGIN_EEA};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();

        yesQuestions=new Question[]{AUTHOR_ANONYMOUS,COUNTRY_OF_ORIGIN_EEA,WORK_PUBLISHED_OR_COMMUNICATED,PUBLISHED_MORE_THAN_70_YEAR_AFTER_CREATION,PUBLISHED_MORE_THAN_25_YEARS_AGO};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

        yesQuestions=new Question[]{AUTHOR_ANONYMOUS,COUNTRY_OF_ORIGIN_EEA,WORK_PUBLISHED_OR_COMMUNICATED,PUBLISHED_MORE_THAN_70_YEAR_AFTER_CREATION};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();

        yesQuestions=new Question[]{AUTHOR_ANONYMOUS,COUNTRY_OF_ORIGIN_EEA,WORK_PUBLISHED_OR_COMMUNICATED,PUBLISHED_MORE_THAN_70_YEARS_AGO};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

        yesQuestions=new Question[]{AUTHOR_ANONYMOUS,COUNTRY_OF_ORIGIN_EEA,WORK_PUBLISHED_OR_COMMUNICATED};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();

        yesQuestions=new Question[]{AUTHOR_ANONYMOUS,AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA,CREATED_MORE_THAN_70_YEARS_AGO};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

        yesQuestions=new Question[]{AUTHOR_ANONYMOUS,AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();

        yesQuestions=new Question[]{AUTHOR_ANONYMOUS,AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA,WORK_PUBLISHED_OR_COMMUNICATED,PUBLISHED_MORE_THAN_70_YEAR_AFTER_CREATION,PUBLISHED_MORE_THAN_25_YEARS_AGO};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

        yesQuestions=new Question[]{AUTHOR_ANONYMOUS,AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA,WORK_PUBLISHED_OR_COMMUNICATED,PUBLISHED_MORE_THAN_70_YEAR_AFTER_CREATION};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();

        yesQuestions=new Question[]{AUTHOR_ANONYMOUS,AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA,WORK_PUBLISHED_OR_COMMUNICATED,PUBLISHED_MORE_THAN_70_YEARS_AGO};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, true).test();

        yesQuestions=new Question[]{AUTHOR_ANONYMOUS,AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA,WORK_PUBLISHED_OR_COMMUNICATED};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();

        yesQuestions=new Question[]{};
        new QuestionnaireTestCase(pdc, LITERARY_OR_ARTISTIC_WORK, yesQuestions, false).test();


        // Tests for NON_ORIGINAL_PHOTOGRAPH
        yesQuestions=new Question[]{WORK_PUBLISHED_OR_COMMUNICATED};
        new QuestionnaireTestCase(pdc, NON_ORIGINAL_PHOTOGRAPH, yesQuestions, false).test();

        yesQuestions=new Question[]{WORK_PUBLISHED_OR_COMMUNICATED,PUBLISHED_MORE_THAN_50_YEARS_AGO};
        new QuestionnaireTestCase(pdc, NON_ORIGINAL_PHOTOGRAPH, yesQuestions, true).test();

        yesQuestions=new Question[]{};
        new QuestionnaireTestCase(pdc, NON_ORIGINAL_PHOTOGRAPH, yesQuestions, false).test();

        yesQuestions=new Question[]{CREATED_MORE_THAN_50_YEARS_AGO};
        new QuestionnaireTestCase(pdc, NON_ORIGINAL_PHOTOGRAPH, yesQuestions, true).test();


        // Tests for SCIENTIFIC_EDITION_OF_AN_OUT_OF_COPYRIGHT_WORK
        yesQuestions=new Question[]{WORK_PUBLISHED_OR_COMMUNICATED,PUBLISHED_MORE_THAN_25_YEARS_AGO};
        new QuestionnaireTestCase(pdc, SCIENTIFIC_EDITION_OF_AN_OUT_OF_COPYRIGHT_WORK, yesQuestions, true).test();

        yesQuestions=new Question[]{WORK_PUBLISHED_OR_COMMUNICATED};
        new QuestionnaireTestCase(pdc, SCIENTIFIC_EDITION_OF_AN_OUT_OF_COPYRIGHT_WORK, yesQuestions, false).test();

        yesQuestions=new Question[]{CREATED_MORE_THAN_25_YEARS_AGO};
        new QuestionnaireTestCase(pdc, SCIENTIFIC_EDITION_OF_AN_OUT_OF_COPYRIGHT_WORK, yesQuestions, true).test();

        yesQuestions=new Question[]{};
        new QuestionnaireTestCase(pdc, SCIENTIFIC_EDITION_OF_AN_OUT_OF_COPYRIGHT_WORK, yesQuestions, false).test();


        // Tests for PHONOGRAM
        yesQuestions=new Question[]{};
        new QuestionnaireTestCase(pdc, PHONOGRAM, yesQuestions, false).test();

        yesQuestions=new Question[]{CREATED_MORE_THAN_50_YEARS_AGO};
        new QuestionnaireTestCase(pdc, PHONOGRAM, yesQuestions, true).test();

        yesQuestions=new Question[]{PUBLISHED_WITHIN_50_YEAR_AFTER_CREATION};
        new QuestionnaireTestCase(pdc, PHONOGRAM, yesQuestions, false).test();

        yesQuestions=new Question[]{PUBLISHED_WITHIN_50_YEAR_AFTER_CREATION,PUBLISHED_MORE_THAN_50_YEARS_AGO};
        new QuestionnaireTestCase(pdc, PHONOGRAM, yesQuestions, true).test();


        // Tests for BROADCAST
        yesQuestions=new Question[]{FIRST_BROADCAST_25_YEARS};
        new QuestionnaireTestCase(pdc, BROADCAST, yesQuestions, true).test();

        yesQuestions=new Question[]{};
        new QuestionnaireTestCase(pdc, BROADCAST, yesQuestions, false).test();


        // Tests for PERFORMANCE
        yesQuestions=new Question[]{PERFORMED_MORE_THAN_50_YEARS_AGO};
        new QuestionnaireTestCase(pdc, PERFORMANCE, yesQuestions, true).test();

        yesQuestions=new Question[]{};
        new QuestionnaireTestCase(pdc, PERFORMANCE, yesQuestions, false).test();

        yesQuestions=new Question[]{FIXATION_PUBLISHED_50_YEARS_FROM_PERFORMANCE,FIXATION_PUBLISHED_MORE_THAN_50_YEARS_AGO};
        new QuestionnaireTestCase(pdc, PERFORMANCE, yesQuestions, true).test();

        yesQuestions=new Question[]{FIXATION_PUBLISHED_50_YEARS_FROM_PERFORMANCE};
        new QuestionnaireTestCase(pdc, PERFORMANCE, yesQuestions, false).test();


        // Tests for UNORIGINAL_DATABASE
        yesQuestions=new Question[]{RIGHT_HOLDER_COMPANY_OR_FIRM};
        new QuestionnaireTestCase(pdc, UNORIGINAL_DATABASE, yesQuestions, true).test();

        yesQuestions=new Question[]{RIGHT_HOLDER_COMPANY_OR_FIRM,COUNTRY_REGISTERED_OFFICE_EEA};
        new QuestionnaireTestCase(pdc, UNORIGINAL_DATABASE, yesQuestions, true).test();

        yesQuestions=new Question[]{RIGHT_HOLDER_COMPANY_OR_FIRM,COUNTRY_REGISTERED_OFFICE_EEA,OPERATION_LINKED_TO_ECONOMY_EEA,PUBLISHED_AFTER_COMPLETION_OR_CHANGE};
        new QuestionnaireTestCase(pdc, UNORIGINAL_DATABASE, yesQuestions, true).test();

        yesQuestions=new Question[]{RIGHT_HOLDER_COMPANY_OR_FIRM,COUNTRY_REGISTERED_OFFICE_EEA,OPERATION_LINKED_TO_ECONOMY_EEA,PUBLISHED_AFTER_COMPLETION_OR_CHANGE,PUBLISHED_IN_LAST_15_YEARS};
        new QuestionnaireTestCase(pdc, UNORIGINAL_DATABASE, yesQuestions, false).test();

        yesQuestions=new Question[]{RIGHT_HOLDER_COMPANY_OR_FIRM,COUNTRY_REGISTERED_OFFICE_EEA,OPERATION_LINKED_TO_ECONOMY_EEA};
        new QuestionnaireTestCase(pdc, UNORIGINAL_DATABASE, yesQuestions, true).test();

        yesQuestions=new Question[]{RIGHT_HOLDER_COMPANY_OR_FIRM,COUNTRY_REGISTERED_OFFICE_EEA,OPERATION_LINKED_TO_ECONOMY_EEA,CHANGED_OR_COMPLETED_WITHIN_LAST_15_YEARS};
        new QuestionnaireTestCase(pdc, UNORIGINAL_DATABASE, yesQuestions, false).test();

        yesQuestions=new Question[]{RIGHT_HOLDER_COMPANY_OR_FIRM,FORMED_UNDER_LAW_OF_EEA};
        new QuestionnaireTestCase(pdc, UNORIGINAL_DATABASE, yesQuestions, true).test();

        yesQuestions=new Question[]{RIGHT_HOLDER_COMPANY_OR_FIRM,FORMED_UNDER_LAW_OF_EEA,REGISTERED_IN_EEA,PUBLISHED_AFTER_COMPLETION_OR_CHANGE};
        new QuestionnaireTestCase(pdc, UNORIGINAL_DATABASE, yesQuestions, true).test();

        yesQuestions=new Question[]{RIGHT_HOLDER_COMPANY_OR_FIRM,FORMED_UNDER_LAW_OF_EEA,REGISTERED_IN_EEA,PUBLISHED_AFTER_COMPLETION_OR_CHANGE,PUBLISHED_IN_LAST_15_YEARS};
        new QuestionnaireTestCase(pdc, UNORIGINAL_DATABASE, yesQuestions, false).test();

        yesQuestions=new Question[]{RIGHT_HOLDER_COMPANY_OR_FIRM,FORMED_UNDER_LAW_OF_EEA,REGISTERED_IN_EEA};
        new QuestionnaireTestCase(pdc, UNORIGINAL_DATABASE, yesQuestions, true).test();

        yesQuestions=new Question[]{RIGHT_HOLDER_COMPANY_OR_FIRM,FORMED_UNDER_LAW_OF_EEA,REGISTERED_IN_EEA,CHANGED_OR_COMPLETED_WITHIN_LAST_15_YEARS};
        new QuestionnaireTestCase(pdc, UNORIGINAL_DATABASE, yesQuestions, false).test();

        yesQuestions=new Question[]{RIGHTSHOLDER_RESIDENT_EEA,PUBLISHED_AFTER_COMPLETION_OR_CHANGE};
        new QuestionnaireTestCase(pdc, UNORIGINAL_DATABASE, yesQuestions, true).test();

        yesQuestions=new Question[]{RIGHTSHOLDER_RESIDENT_EEA,PUBLISHED_AFTER_COMPLETION_OR_CHANGE,PUBLISHED_IN_LAST_15_YEARS};
        new QuestionnaireTestCase(pdc, UNORIGINAL_DATABASE, yesQuestions, false).test();

        yesQuestions=new Question[]{RIGHTSHOLDER_RESIDENT_EEA};
        new QuestionnaireTestCase(pdc, UNORIGINAL_DATABASE, yesQuestions, true).test();

        yesQuestions=new Question[]{RIGHTSHOLDER_RESIDENT_EEA,CHANGED_OR_COMPLETED_WITHIN_LAST_15_YEARS};
        new QuestionnaireTestCase(pdc, UNORIGINAL_DATABASE, yesQuestions, false).test();

        yesQuestions=new Question[]{};
        new QuestionnaireTestCase(pdc, UNORIGINAL_DATABASE, yesQuestions, true).test();


        // Tests for FIRST_FIXATION_OF_A_FILM
        yesQuestions=new Question[]{};
        new QuestionnaireTestCase(pdc, FIRST_FIXATION_OF_A_FILM, yesQuestions, false).test();

        yesQuestions=new Question[]{CREATED_MORE_THAN_50_YEARS_AGO};
        new QuestionnaireTestCase(pdc, FIRST_FIXATION_OF_A_FILM, yesQuestions, true).test();

        yesQuestions=new Question[]{PUBLISHED_WITHIN_50_YEAR_AFTER_CREATION};
        new QuestionnaireTestCase(pdc, FIRST_FIXATION_OF_A_FILM, yesQuestions, false).test();

        yesQuestions=new Question[]{PUBLISHED_WITHIN_50_YEAR_AFTER_CREATION,PUBLISHED_MORE_THAN_50_YEARS_AGO};
        new QuestionnaireTestCase(pdc, FIRST_FIXATION_OF_A_FILM, yesQuestions, true).test();
    }
}