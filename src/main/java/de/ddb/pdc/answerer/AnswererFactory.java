package de.ddb.pdc.answerer;

import de.ddb.pdc.core.Question;

public class AnswererFactory {
  
  /**
   * Creates and returns a specific implementation of {@link Answerer} depending
   * on the type of question provided.
   * @param question
   * @return required Answerer implementation
   * @throws de.ddb.pdc.answerer.UnsupportedQuestionException
   */
  public static Answerer getAnswererForQuestion(Question question) 
      throws UnsupportedQuestionException {
    switch(question) {
      case AUTHOR_FROM_TRIPTIS:
        return new AnswererImplAFT();
      case COUNTRY_OF_ORIGIN_TRIPTIS:
        return new AnswererImplCOOT();
      case CREATED_MORE_THAN_70_YEARS_AGO:
        return new AnswererImplCMT70YA();
      case PUBLISHED_MORE_THAN_25_YEARS_AGO:
        return new AnswererImplPMT25YA();
      case PUBLISHED_MORE_THAN_70_YEAR_AFTER_CREATION:
        return new AnswererImplPMT70YAC();
      case WORK_PUBLISHED_OR_COMMUNICATED:
        return new AnswererImplWPOC();
      case COUNTRY_OF_ORIGIN_EEA:
        return new AnswererImplCOOE();
      case AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA:
        return new AnswererImplAFEEA();
      case AUTHOR_ANONYMOUS:
        return new AnswererImplAA();
      case AUTHOR_DIED_MORE_THAN_70_YEARS_AGO:
        return new AnswererImplADMT70YA();
      case PUBLISHED_WITHIN_70_YEARS_OF_DEATH:
        return new AnswererImplPW70YOD();
      case AUTHOR_NATURAL_PERSON:
        return new AnswererImplANP();
      case ANNOUNCEMENT_BY_AUTHORITY:
        return new AnswererImplABA();
      case GOVERNMENT_DIRECTIVE:
        return new AnswererImplGD();
      case ACT_OF_PARLIAMENT:
        return new AnswererImplAOP();
      case COURT_DECISION_OR_DECISION_FORMULA:
        return new AnswererImplCDODF();
      case OFFICIAL_WORK_INTENDED_TO_BE_GENERALLY_RECEIVED:
        return new AnswererImplOWITBGR();      
      default:
        throw new UnsupportedQuestionException(question);
    }
  } 
}
