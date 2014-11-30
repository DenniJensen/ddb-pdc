package de.ddb.pdc.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.ddb.pdc.core.answerers.AnswererFactory;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Run the PDC for test data defined in DDBItem.
 * This class implements the AnswererService interface
 */
@Service
public class PublicDomainCalculatorImpl implements PublicDomainCalculator {
  private final QuestionnaireFactory questionnaireFactory;
  private final AnswererFactory answererFactory;

  /**
   * Creates a new PublicDomainCalculatorImpl.
   *
   * @param questionnaireFactory {@link QuestionnaireFactory} to use
   * @param answererFactory {@link QuestionnaireFactory}
   */
  @Autowired
  public PublicDomainCalculatorImpl(
      QuestionnaireFactory questionnaireFactory,
      AnswererFactory answererFactory) {
    this.questionnaireFactory = questionnaireFactory;
    this.answererFactory = answererFactory;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public PDCResult calculate(String country, DDBItem metadata) {
    // FIXME: HardCoded
    Category category = Category.LITERARY_OR_ARTISTIC_WORK;
    Questionnaire questionnaire = questionnaireFactory.build(country, category);
    answerQuestions(questionnaire, metadata);
    return questionnaire.getResult();
  }

  private void answerQuestions(Questionnaire questionnaire, DDBItem metadata) {
    while (questionnaire.hasQuestionsLeft()) {
      Question question = questionnaire.getCurrentQuestion();
      Answerer answerer = answererFactory.getAnswererForQuestion(question);
      Answer answer = answerer.answerQuestionForItem(metadata);
      String assumption = answerer.getAssumptionForLastAnswer();
      questionnaire.answerCurrentQuestion(answer, assumption);
    }
  }
}
