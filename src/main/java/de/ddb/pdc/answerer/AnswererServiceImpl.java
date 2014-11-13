package de.ddb.pdc.answerer;

import de.ddb.pdc.core.Answer;
import de.ddb.pdc.core.PublicDomainCalculator;
import de.ddb.pdc.core.PublicDomainCalculatorFactory;
import de.ddb.pdc.core.Category;
import de.ddb.pdc.core.Question;
import de.ddb.pdc.core.Questionnaire;
import de.ddb.pdc.core.UnsupportedCategoryException;
import de.ddb.pdc.core.UnsupportedCountryException;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Run the PDC for test data defined in DDBItem.
 * 
 */
public class AnswererServiceImpl implements AnswererService {
  
  /**
   * 
   * @param country
   * @param metadata
   * @return
   * @throws UnsupportedCountryException
   * @throws UnsupportedCategoryException
   * @throws UnsupportedQuestionException
   */
  @Override
  public Result getResult(String country, DDBItem metadata) 
      throws UnsupportedCountryException, UnsupportedCategoryException,
      UnsupportedQuestionException {
    
    // --- initialize the calculator
    System.out.println("Starting pdc");
    PublicDomainCalculator pdc = PublicDomainCalculatorFactory.getCalculator(country);
    Category category = Category.valueOf(metadata.category);
    Questionnaire questionnaire = pdc.startQuestionnaire(category);    
    Question currentQuestion;
    Answerer answerer;
    Answer answer;
    
    while (questionnaire.hasQuestions()) {
      // --- process each question    
      currentQuestion = questionnaire.getCurrentQuestion();
      System.out.println("Question: " + currentQuestion.getText());
      answerer = AnswererFactory.getAnswererForQuestion(currentQuestion);
      answer = answerer.getAnswer(metadata);
      System.out.println("Answer: " + answer.name());
      questionnaire.answerCurrentQuestion(answer);
    }
    
    // --- prepare and return the final result
    boolean isPublicDomain = questionnaire.isPublicDomain();
    System.out.println("Public domain:" + isPublicDomain);
    
    return null;
  }
  
}
