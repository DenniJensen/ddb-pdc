package de.ddb.pdc.answerer;

import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the AUTHOR_NATURAL_PERSON question.
 * 
 * TODO clarify how this is determined by the fetcher.
 */
public class AnswererImplANP implements Answerer {

  @Override
  public Answer getAnswer(DDBItem metaData) {
    return Answer.YES;
  }
  
}
