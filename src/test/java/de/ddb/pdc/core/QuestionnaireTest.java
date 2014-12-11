package de.ddb.pdc.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuestionnaireTest {

@Test
  public void literaryOrArtisticWorkFlowChart(){
    FlowChartStateGermany flowChartGermany =
            FlowChartStateGermany.LITERATUR_OR_ARTISTIC_WORK;

    Questionnaire questionaireA = new Questionnaire(flowChartGermany);
    questionaireA.answerCurrentQuestion(Answer.NO, null);
    questionaireA.answerCurrentQuestion(Answer.NO, null);
    questionaireA.answerCurrentQuestion(Answer.NO, null);
    questionaireA.answerCurrentQuestion(Answer.NO, null);
    questionaireA.answerCurrentQuestion(Answer.YES, null);
    assertTrue(questionaireA.isPublicDomain());

    Questionnaire questionaireB = new Questionnaire(flowChartGermany);
    questionaireB.answerCurrentQuestion(Answer.NO, null);
    questionaireB.answerCurrentQuestion(Answer.YES, null);
    assertTrue(questionaireB.isPublicDomain());

    Questionnaire questionaireC = new Questionnaire(flowChartGermany);
    questionaireC.answerCurrentQuestion(Answer.NO, null);
    questionaireC.answerCurrentQuestion(Answer.NO, null);
    questionaireC.answerCurrentQuestion(Answer.NO, null);
    questionaireC.answerCurrentQuestion(Answer.NO, null);
    questionaireC.answerCurrentQuestion(Answer.NO, null);
    questionaireC.answerCurrentQuestion(Answer.YES, null);
    questionaireC.answerCurrentQuestion(Answer.YES, null);
    questionaireC.answerCurrentQuestion(Answer.NO, null);
    questionaireC.answerCurrentQuestion(Answer.YES, null);
    assertTrue(questionaireC.isPublicDomain());
  }

  @Test
  public void nonOriginalPhotographFlowChart(){
    FlowChartStateGermany flowChartGermany =
            FlowChartStateGermany.NON_ORIGINAL_PHOTOGRAPH;

    Questionnaire questionaireA = new Questionnaire(flowChartGermany);
    questionaireA.answerCurrentQuestion(Answer.YES, null);
    questionaireA.answerCurrentQuestion(Answer.NO, null);
    assertFalse(questionaireA.isPublicDomain());

    Questionnaire questionaireB = new Questionnaire(flowChartGermany);
    questionaireB.answerCurrentQuestion(Answer.NO, null);
    questionaireB.answerCurrentQuestion(Answer.YES, null);
    assertTrue(questionaireB.isPublicDomain());
  }

  @Test
  public void scientificEditionFlowChart(){
    FlowChartStateGermany flowChartGermany =
            FlowChartStateGermany.SCIENTIFIC_EDITION_OF_OOC_WORK;

    Questionnaire questionaireA = new Questionnaire(flowChartGermany);
    questionaireA.answerCurrentQuestion(Answer.YES, null);
    questionaireA.answerCurrentQuestion(Answer.YES, null);
    assertTrue(questionaireA.isPublicDomain());

    Questionnaire questionaireB = new Questionnaire(flowChartGermany);
    questionaireB.answerCurrentQuestion(Answer.NO, null);
    questionaireB.answerCurrentQuestion(Answer.YES, null);
    assertTrue(questionaireB.isPublicDomain());
  }

  @Test
  public void phonogramFlowChart(){
    FlowChartStateGermany flowChartGermany =
            FlowChartStateGermany.PHONOGRAM;

    Questionnaire questionaireA = new Questionnaire(flowChartGermany);
    questionaireA.answerCurrentQuestion(Answer.YES, null);
    questionaireA.answerCurrentQuestion(Answer.NO, null);
    assertFalse(questionaireA.isPublicDomain());

    Questionnaire questionaireB = new Questionnaire(flowChartGermany);
    questionaireB.answerCurrentQuestion(Answer.NO, null);
    questionaireB.answerCurrentQuestion(Answer.YES, null);
    questionaireB.answerCurrentQuestion(Answer.YES, null);
    assertTrue(questionaireB.isPublicDomain());
  }

  @Test
  public void performanceFlowChart(){
    FlowChartStateGermany flowChartGermany = FlowChartStateGermany.PERFORMANCE;

    Questionnaire questionaireA = new Questionnaire(flowChartGermany);
    questionaireA.answerCurrentQuestion(Answer.NO, null);
    questionaireA.answerCurrentQuestion(Answer.YES, null);
    assertTrue(questionaireA.isPublicDomain());

    Questionnaire questionaireB = new Questionnaire(flowChartGermany);
    questionaireB.answerCurrentQuestion(Answer.YES, null);
    questionaireB.answerCurrentQuestion(Answer.NO, null);
    assertFalse(questionaireB.isPublicDomain());
  }

  @Test
  public void unoriginalDatabaseFlowChart(){
    FlowChartStateGermany flowChartGermany = FlowChartStateGermany.DATABASE;

    Questionnaire questionaireA = new Questionnaire(flowChartGermany);
    questionaireA.answerCurrentQuestion(Answer.YES, null);
    questionaireA.answerCurrentQuestion(Answer.YES, null);
    questionaireA.answerCurrentQuestion(Answer.NO, null);
    assertTrue(questionaireA.isPublicDomain());

    Questionnaire questionaireB = new Questionnaire(flowChartGermany);
    questionaireB.answerCurrentQuestion(Answer.NO, null);
    questionaireB.answerCurrentQuestion(Answer.NO, null);
    assertTrue(questionaireB.isPublicDomain());

    Questionnaire questionaireC = new Questionnaire(flowChartGermany);
    questionaireC.answerCurrentQuestion(Answer.NO, null);
    questionaireC.answerCurrentQuestion(Answer.YES, null);
    questionaireC.answerCurrentQuestion(Answer.NO, null);
    questionaireC.answerCurrentQuestion(Answer.NO, null);
    assertTrue(questionaireC.isPublicDomain());

    Questionnaire questionaireD = new Questionnaire(flowChartGermany);
    questionaireD.answerCurrentQuestion(Answer.NO, null);
    questionaireD.answerCurrentQuestion(Answer.YES, null);
    questionaireD.answerCurrentQuestion(Answer.YES, null);
    questionaireD.answerCurrentQuestion(Answer.YES, null);
    assertFalse(questionaireD.isPublicDomain());
  }

  @Test
  public void firstFixationOfAFilmFlowChart(){
    FlowChartStateGermany flowChartGermany =
            FlowChartStateGermany.FIXATION_OF_FILM;

    Questionnaire questionaireA = new Questionnaire(flowChartGermany);
    questionaireA.answerCurrentQuestion(Answer.YES, null);
    questionaireA.answerCurrentQuestion(Answer.NO, null);
    assertFalse(questionaireA.isPublicDomain());

    Questionnaire questionaireB = new Questionnaire(flowChartGermany);
    questionaireB.answerCurrentQuestion(Answer.NO, null);
    questionaireB.answerCurrentQuestion(Answer.YES, null);
    assertTrue(questionaireB.isPublicDomain());
  }

}
