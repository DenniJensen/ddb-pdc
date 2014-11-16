package de.ddb.pdc.answerer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.ddb.pdc.core.Answer;
import de.ddb.pdc.core.Category;
import de.ddb.pdc.core.PDCResult;
import de.ddb.pdc.core.PublicDomainCalculator;
import de.ddb.pdc.core.PublicDomainCalculatorFactory;
import de.ddb.pdc.core.Question;
import de.ddb.pdc.core.Questionnaire;
import de.ddb.pdc.core.UnsupportedCategoryException;
import de.ddb.pdc.core.UnsupportedCountryException;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Run the PDC for test data defined in DDBItem.
 * This class implements the AnswererService interface
 */
@Service
public class AnswererServiceImpl implements AnswererService {

  @Autowired
  private PublicDomainCalculatorFactory calculatorFactory;

  @Autowired
  private AnswererFactory answererFactory;

  /**
   * {@inheritDoc}
   */
  @Override
  public PDCResult getResult(String country, DDBItem metadata)
      throws UnsupportedCountryException, UnsupportedCategoryException,
      UnsupportedQuestionException {

    // get the public domain calculator for the country
    PublicDomainCalculator pdc = this.calculatorFactory.getCalculator(country);

    // get the category of the cultural good
    Category category;
    try {
      category = Category.valueOf(metadata.getCategory());
    } catch (IllegalArgumentException e) {
      throw new UnsupportedCategoryException(null);
    }

    // get the questionnaire for this cultural good
    Questionnaire questionnaire = pdc.startQuestionnaire(category);
    Question currentQuestion;
    Answerer answerer;
    Answer answer;

    // answer all the questions of the questionnaire
    while (questionnaire.hasQuestions()) {
      currentQuestion = questionnaire.getCurrentQuestion();
      answerer = this.answererFactory.getAnswererForQuestion(currentQuestion);
      answer = answerer.getAnswer(metadata);
      questionnaire.answerCurrentQuestion(answer);
    }

    // build and return the result
    boolean isPublicDomain = questionnaire.isPublicDomain();
    PDCResult result = new PDCResult(isPublicDomain, questionnaire.getTrace());
    return result;
  }
}
