package de.ddb.pdc.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class QuestionnaireTestCase {
  private final PublicDomainCalculator pdc;
  private final Category testCategory;
  private final Question[] yesAnsweredQuestions;
  private final Boolean expectedResult;
  private Questionnaire questionnaire;

  public QuestionnaireTestCase(PublicDomainCalculator pdc, Category testCategory,
                               Question[] yesAnsweredQuestions, Boolean expectedResult){
    this.pdc = pdc;
    this.testCategory = testCategory;
    this.yesAnsweredQuestions = yesAnsweredQuestions;
    this.expectedResult = expectedResult;
  }

  public void test(){
    try {
      questionnaire = pdc.startQuestionnaire(testCategory);
    } catch (UnsupportedCategoryException e) {
      assertFalse("UnsupportedCategoryException: " + testCategory, true);
    }

    Integer lastElement = yesAnsweredQuestions.length - 1;
    Integer i = 0;
    String path = "";
    Boolean allAnswersUsed = false;

    if (lastElement == -1){
      path = "[all answers NO]";
      allAnswersUsed = true;
      while (questionnaire.hasQuestionsLeft()) {questionnaire.answerCurrentQuestion(Answer.NO, null);}
    }

    while (questionnaire.hasQuestionsLeft()){

      if (questionnaire.getCurrentQuestion() == yesAnsweredQuestions[i]){
        path= path + questionnaire.getCurrentQuestion() + " ";
        questionnaire.answerCurrentQuestion(Answer.YES, null);
        if (lastElement > i) {
          i++;
        }
        else {
          allAnswersUsed = true;
        }
      }
      else {
        questionnaire.answerCurrentQuestion(Answer.NO, null);
      }
    }

    assertTrue("Not all yesAnsweredQuestions have been asked. Your test might be flawed.",allAnswersUsed);
    Boolean result = questionnaire.isPublicDomain();
    assertEquals("Wrong result for "+testCategory+": "+path,expectedResult,result);
  }
}
