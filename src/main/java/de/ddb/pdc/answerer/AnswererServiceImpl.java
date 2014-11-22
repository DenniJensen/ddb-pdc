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
import de.ddb.pdc.metadata.DDBItem;

/**
 * Run the PDC for test data defined in DDBItem.
 * This class implements the AnswererService interface
 */
@Service
public class AnswererServiceImpl implements AnswererService {

  private final PublicDomainCalculatorFactory calculatorFactory;

  private final AnswererFactory answererFactory;

  /**
   * Creates a new AnswererService that uses the given
   * {@link PublicDomainCalculatorFactory} and the given
   * {@link AnswererFactory} to decide the public domain problem.
   * @param calculatorFactory the public domain calculator factory instance
   * @param answererFactory the answerer factory instance
   */
  @Autowired
  public AnswererServiceImpl(PublicDomainCalculatorFactory calculatorFactory,
      AnswererFactory answererFactory) {
    this.calculatorFactory = calculatorFactory;
    this.answererFactory = answererFactory;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public PDCResult getResult(String country, DDBItem metadata) {

    PublicDomainCalculator pdc = this.calculatorFactory.getCalculator(country);

    // FIXME: HardCoded
    Category category = Category.LITERARY_OR_ARTISTIC_WORK;

    Questionnaire questionnaire = pdc.startQuestionnaire(category);
    Question currentQuestion;
    Answerer answerer;
    Answer answer;

    while (questionnaire.hasQuestions()) {
      currentQuestion = questionnaire.getCurrentQuestion();
      answerer = this.answererFactory.getAnswererForQuestion(currentQuestion);
      answer = answerer.getAnswer(metadata);
      questionnaire.answerCurrentQuestion(answer);
    }

    return questionnaire.getResult();
  }
}
