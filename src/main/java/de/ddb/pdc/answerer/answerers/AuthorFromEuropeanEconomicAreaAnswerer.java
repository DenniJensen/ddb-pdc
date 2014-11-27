package de.ddb.pdc.answerer.answerers;

import java.util.List;

import de.ddb.pdc.answerer.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.Author;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA question.
 */
class AuthorFromEuropeanEconomicAreaAnswerer implements Answerer {

  /**
   * Answer whether the author's country is a member of the EEA.
   */
  @Override
  public Answer answerQuestionForItem(DDBItem metaData) {
    if (metaData.getAuthors().isEmpty()) {
      return Answer.UNKNOWN;
    } 
    List<Author> authors = metaData.getAuthors();
    for (Author author : authors) {
      if (!EEAMembers.isMember(author.getNationality())) {
        return Answer.NO;
      }
    }
    return Answer.YES;
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public String getAssumptionForLastAnswer() {
    return null;
  }
}
