package de.ddb.pdc.core;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class QuestionnaireFactoryTest {

  @Test
  public void germanyLiteraryOrArtisticWork() {
    QuestionnaireFactory factory = new QuestionnaireFactory();
    Category category = Category.LITERARY_OR_ARTISTIC_WORK;
    Questionnaire questionnaire = factory.build("de", category, null);
    assertEquals(Question.OFFICIAL_WORK_INTENDED_TO_BE_GENERALLY_RECEIVED,
        questionnaire.getCurrentQuestion());
  }

  @Test(expected = UnsupportedCountryException.class)
  public void unsupportedCountry() {
    new QuestionnaireFactory().build(
        "bla", Category.LITERARY_OR_ARTISTIC_WORK, null
    );
  }
}
