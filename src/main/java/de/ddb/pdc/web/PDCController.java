package de.ddb.pdc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.ddb.pdc.core.Answer;
import de.ddb.pdc.core.Category;
import de.ddb.pdc.core.PDCResult;
import de.ddb.pdc.core.PublicDomainCalculator;
import de.ddb.pdc.core.PublicDomainCalculatorFactory;
import de.ddb.pdc.core.Questionnaire;

/**
 * Provides an HTTP interface for public domain calculation.
 */
@RestController
public class PDCController {
  private final PublicDomainCalculatorFactory calculatorFactory;

  /**
   * Creates a PDCController.
   *
   * @param calculatorFactory factory to receive
   *                          {@link PublicDomainCalculator} objects from
   */
  @Autowired
  public PDCController(PublicDomainCalculatorFactory calculatorFactory) {
    this.calculatorFactory = calculatorFactory;
  }

  /**
   * Retrieves or calculates a public-domain evaluation of a DDB item.
   * Requests must be of the form
   *
   *   GET /pdc/ITEM_ID
   *
   * where ITEM_ID is the ID of an item in the DDB database. The result is
   * returned as a JSON object with the following attributes:
   *
   *   publicDomain - true the item is considered public-domain by the
   *                  calculator, or false if not.
   *
   *   trace        - An array of the questions asked and their answers.
   *                  Each question is represented as a JSON object with
   *                  two attributes - "question", which contains the
   *                  full text of the question, and "answer", which
   *                  is true if the answer was answered with "yes" and
   *                  false if the answer was "no".
   *
   * @param itemId DDB item ID
   * @return
   */
  @RequestMapping("/pdc/{itemId}")
  public PDCResult calculate(@PathVariable String itemId) throws Exception {
    // TODO: Replace dummy implementation.

    PublicDomainCalculator calculator =
        this.calculatorFactory.getCalculator("de");
    Questionnaire questionnaire =
        calculator.startQuestionnaire(Category.LITERARY_OR_ARTISTIC_WORK);

    questionnaire.answerCurrentQuestion(Answer.NO);
    questionnaire.answerCurrentQuestion(Answer.NO);
    questionnaire.answerCurrentQuestion(Answer.YES);

    PDCResult result =
        new PDCResult(questionnaire.isPublicDomain(), questionnaire.getTrace());

    return result;
  }
}
