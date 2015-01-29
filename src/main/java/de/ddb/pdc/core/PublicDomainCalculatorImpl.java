package de.ddb.pdc.core;

import java.util.LinkedList;
import java.util.List;

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
    // Check if an item already has a public domain license.
    if (metadata.hasCcLicense()) {
      String cc = metadata.getCcLicense();
      String link = "https://creativecommons.org/licenses/" + cc;
      if (cc.equals("zero")) {
        link += "/1.0/";
      } else {
        link += "/4.0/";
      }
      List<AnsweredQuestion> trace = new LinkedList<AnsweredQuestion>();
      trace.add(new AnsweredQuestion(Question.IS_PUBLIC_DOMAIN, 
          Answer.YES, "Das Werk steht bereits unter einer CC Lizenz"
          + ": CC-" + cc + ". Lizenzinformationen unter: " + link));
      PDCResult result = new PDCResult(true, trace, metadata);
      return result;
    }
    
    // FIXME: Category is hard coded
    Category category = Category.LITERARY_OR_ARTISTIC_WORK;
    Questionnaire questionnaire = questionnaireFactory.build(
        country, category, metadata
    );
    answerQuestions(questionnaire, metadata);
    return questionnaire.getResult();
  }

  private void answerQuestions(Questionnaire questionnaire, DDBItem metadata) {
    while (questionnaire.hasQuestionsLeft()) {
      Question question = questionnaire.getCurrentQuestion();
      Answerer answerer = answererFactory.getAnswererForQuestion(question);
      Answer answer = answerer.answerQuestionForItem(metadata);
      String assumption = answerer.getNoteForLastQuestion();
      questionnaire.answerCurrentQuestion(answer, assumption);
    }
  }
}
