package de.ddb.pdc.core;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class QuestionnaireTest {

  @Test
  public void literaryOrArtisticWorkFlowChart() {
    FlowChartStateGermany flowChartGermany =
        FlowChartStateGermany.LITERATUR_OR_ARTISTIC_WORK;

    Questionnaire questionaireA = new Questionnaire(flowChartGermany, null);
    questionaireA.answerCurrentQuestion(Answer.NO, null);
    questionaireA.answerCurrentQuestion(Answer.NO, null);
    questionaireA.answerCurrentQuestion(Answer.NO, null);
    questionaireA.answerCurrentQuestion(Answer.NO, null);
    questionaireA.answerCurrentQuestion(Answer.YES, null);
    assertTrue(questionaireA.isPublicDomain());

    Questionnaire questionaireB = new Questionnaire(flowChartGermany, null);
    questionaireB.answerCurrentQuestion(Answer.NO, null);
    questionaireB.answerCurrentQuestion(Answer.YES, null);
    assertTrue(questionaireB.isPublicDomain());

    Questionnaire questionaireC = new Questionnaire(flowChartGermany, null);
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

    Questionnaire questionaireD = new Questionnaire(flowChartGermany, null);
    questionaireD.answerCurrentQuestion(Answer.NO, null);
    questionaireD.answerCurrentQuestion(Answer.NO, null);
    questionaireD.answerCurrentQuestion(Answer.NO, null);
    questionaireD.answerCurrentQuestion(Answer.NO, null);
    questionaireD.answerCurrentQuestion(Answer.NO, null);
    questionaireD.answerCurrentQuestion(Answer.NO, null);
    questionaireD.answerCurrentQuestion(Answer.YES, null);
    questionaireD.answerCurrentQuestion(Answer.YES, null);
    questionaireD.answerCurrentQuestion(Answer.YES, null);
    questionaireD.answerCurrentQuestion(Answer.YES, null);
    questionaireD.answerCurrentQuestion(Answer.NO, null);
    assertFalse(questionaireD.isPublicDomain());
  }

  @Test
  public void nonOriginalPhotographFlowChart() {
    FlowChartStateGermany flowChartGermany =
        FlowChartStateGermany.NON_ORIGINAL_PHOTOGRAPH;

    Questionnaire questionaireA = new Questionnaire(flowChartGermany, null);
    questionaireA.answerCurrentQuestion(Answer.YES, null);
    questionaireA.answerCurrentQuestion(Answer.NO, null);
    assertFalse(questionaireA.isPublicDomain());

    Questionnaire questionaireB = new Questionnaire(flowChartGermany, null);
    questionaireB.answerCurrentQuestion(Answer.NO, null);
    questionaireB.answerCurrentQuestion(Answer.YES, null);
    assertTrue(questionaireB.isPublicDomain());
  }

  @Test
  public void scientificEditionFlowChart() {
    FlowChartStateGermany flowChartGermany =
        FlowChartStateGermany.SCIENTIFIC_EDITION_OF_OOC_WORK;

    Questionnaire questionaireA = new Questionnaire(flowChartGermany, null);
    questionaireA.answerCurrentQuestion(Answer.YES, null);
    questionaireA.answerCurrentQuestion(Answer.YES, null);
    assertTrue(questionaireA.isPublicDomain());

    Questionnaire questionaireB = new Questionnaire(flowChartGermany, null);
    questionaireB.answerCurrentQuestion(Answer.NO, null);
    questionaireB.answerCurrentQuestion(Answer.YES, null);
    assertTrue(questionaireB.isPublicDomain());
  }

  @Test
  public void phonogramFlowChart() {
    FlowChartStateGermany flowChartGermany =
        FlowChartStateGermany.PHONOGRAM;

    Questionnaire questionaireA = new Questionnaire(flowChartGermany, null);
    questionaireA.answerCurrentQuestion(Answer.YES, null);
    questionaireA.answerCurrentQuestion(Answer.NO, null);
    assertFalse(questionaireA.isPublicDomain());

    Questionnaire questionaireB = new Questionnaire(flowChartGermany, null);
    questionaireB.answerCurrentQuestion(Answer.NO, null);
    questionaireB.answerCurrentQuestion(Answer.YES, null);
    questionaireB.answerCurrentQuestion(Answer.YES, null);
    assertTrue(questionaireB.isPublicDomain());
  }

  @Test
  public void performanceFlowChart() {
    FlowChartStateGermany flowChartGermany = FlowChartStateGermany.PERFORMANCE;

    Questionnaire questionaireA = new Questionnaire(flowChartGermany, null);
    questionaireA.answerCurrentQuestion(Answer.NO, null);
    questionaireA.answerCurrentQuestion(Answer.YES, null);
    assertTrue(questionaireA.isPublicDomain());

    Questionnaire questionaireB = new Questionnaire(flowChartGermany, null);
    questionaireB.answerCurrentQuestion(Answer.YES, null);
    questionaireB.answerCurrentQuestion(Answer.NO, null);
    assertFalse(questionaireB.isPublicDomain());
  }

  @Test
  public void unoriginalDatabaseFlowChart() {
    FlowChartStateGermany flowChartGermany = FlowChartStateGermany.DATABASE;

    Questionnaire questionaireA = new Questionnaire(flowChartGermany, null);
    questionaireA.answerCurrentQuestion(Answer.YES, null);
    questionaireA.answerCurrentQuestion(Answer.YES, null);
    questionaireA.answerCurrentQuestion(Answer.NO, null);
    assertTrue(questionaireA.isPublicDomain());

    Questionnaire questionaireB = new Questionnaire(flowChartGermany, null);
    questionaireB.answerCurrentQuestion(Answer.NO, null);
    questionaireB.answerCurrentQuestion(Answer.NO, null);
    assertTrue(questionaireB.isPublicDomain());

    Questionnaire questionaireC = new Questionnaire(flowChartGermany, null);
    questionaireC.answerCurrentQuestion(Answer.NO, null);
    questionaireC.answerCurrentQuestion(Answer.YES, null);
    questionaireC.answerCurrentQuestion(Answer.NO, null);
    questionaireC.answerCurrentQuestion(Answer.NO, null);
    assertTrue(questionaireC.isPublicDomain());

    Questionnaire questionaireD = new Questionnaire(flowChartGermany, null);
    questionaireD.answerCurrentQuestion(Answer.NO, null);
    questionaireD.answerCurrentQuestion(Answer.YES, null);
    questionaireD.answerCurrentQuestion(Answer.YES, null);
    questionaireD.answerCurrentQuestion(Answer.YES, null);
    assertFalse(questionaireD.isPublicDomain());
  }

  @Test
  public void firstFixationOfAFilmFlowChart() {
    FlowChartStateGermany flowChartGermany =
        FlowChartStateGermany.FIXATION_OF_FILM;

    Questionnaire questionaireA = new Questionnaire(flowChartGermany, null);
    questionaireA.answerCurrentQuestion(Answer.YES, null);
    questionaireA.answerCurrentQuestion(Answer.NO, null);
    assertFalse(questionaireA.isPublicDomain());

    Questionnaire questionaireB = new Questionnaire(flowChartGermany, null);
    questionaireB.answerCurrentQuestion(Answer.NO, null);
    questionaireB.answerCurrentQuestion(Answer.YES, null);
    assertTrue(questionaireB.isPublicDomain());
  }

  @Test
  public void checkPDCResult() {
    FlowChartStateGermany flowChartGermany =
        FlowChartStateGermany.LITERATUR_OR_ARTISTIC_WORK;

    List<AnsweredQuestion> expectedTrace = new ArrayList<AnsweredQuestion>();
    expectedTrace.add(new AnsweredQuestion(
        Question.OFFICIAL_WORK_INTENDED_TO_BE_GENERALLY_RECEIVED,
        Answer.NO, null));
    expectedTrace.add(new AnsweredQuestion(
        Question.COURT_DECISION_OR_DECISION_FORMULA, Answer.NO, null));
    expectedTrace.add(new AnsweredQuestion(
        Question.ACT_OF_PARLIAMENT, Answer.NO, null));
    expectedTrace.add(new AnsweredQuestion(
        Question.GOVERNMENT_DIRECTIVE, Answer.NO, null));
    expectedTrace.add(new AnsweredQuestion(
        Question.ANNOUNCEMENT_BY_AUTHORITY, Answer.NO, null));
    expectedTrace.add(new AnsweredQuestion(
        Question.AUTHOR_NATURAL_PERSON, Answer.YES, null));
    expectedTrace.add(new AnsweredQuestion(
        Question.AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA, Answer.YES, null));
    expectedTrace.add(new AnsweredQuestion(
        Question.WORK_PUBLISHED_OR_COMMUNICATED, Answer.YES, null));
    expectedTrace.add(new AnsweredQuestion(
        Question.PUBLISHED_WITHIN_70_YEARS_OF_DEATH, Answer.YES, null));
    expectedTrace.add(new AnsweredQuestion(
        Question.AUTHOR_DIED_MORE_THAN_70_YEARS_AGO, Answer.NO, null));

    Questionnaire questionaire = new Questionnaire(flowChartGermany, null);
    questionaire.answerCurrentQuestion(Answer.NO, null);
    questionaire.answerCurrentQuestion(Answer.NO, null);
    questionaire.answerCurrentQuestion(Answer.NO, null);
    questionaire.answerCurrentQuestion(Answer.NO, null);
    questionaire.answerCurrentQuestion(Answer.NO, null);
    questionaire.answerCurrentQuestion(Answer.YES, null);
    questionaire.answerCurrentQuestion(Answer.YES, null);
    questionaire.answerCurrentQuestion(Answer.YES, null);
    questionaire.answerCurrentQuestion(Answer.YES, null);
    questionaire.answerCurrentQuestion(Answer.NO, null);

    for(int i = 0; i < questionaire.getTrace().size(); i++) {
      assertTrue(expectedTrace.get(i).getQuestion().equals(
          questionaire.getTrace().get(i).getQuestion()));
      assertTrue(expectedTrace.get(i).getAnswer().equals(
          questionaire.getTrace().get(i).getAnswer()));
    }
    assertFalse(questionaire.isPublicDomain());
  }



}
