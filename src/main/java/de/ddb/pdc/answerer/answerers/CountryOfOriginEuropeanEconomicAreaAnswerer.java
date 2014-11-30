package de.ddb.pdc.answerer.answerers;

import java.util.List;

import de.ddb.pdc.answerer.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.Author;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the COUNTRY_OF_ORIGIN_EEA question.
 */
class CountryOfOriginEuropeanEconomicAreaAnswerer implements Answerer {

  /**
   * Answer whether the country that the item was created in is a member of the
   * EEA.
   *
   * FIXME there is no nationality-field of the work at the moment
   * FIXME this is mis-using the author-nationality.
   */
  @Override
  public Answer answerQuestionForItem(DDBItem metaData) {
    // FIXME wrong nationality used here
    List<Author> authors = metaData.getAuthors();
    if (authors == null || authors.isEmpty()) {
      return Answer.UNKNOWN;
    }
    // FIXME only nationality of first author used
    String country = authors.get(0).getNationality();
    if (EEAMembers.isMember(country)) {
      return Answer.YES;
    } else {
      return Answer.NO;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getNoteForLastQuestion() {
    return null;
  }
}
