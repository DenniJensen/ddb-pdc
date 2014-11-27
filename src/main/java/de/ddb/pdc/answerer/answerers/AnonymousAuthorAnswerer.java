package de.ddb.pdc.answerer.answerers;

import de.ddb.pdc.answerer.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

/**
 * Answers the AUTHOR_ANONYMOUS question.
 */
class AnonymousAuthorAnswerer implements Answerer {

  private String assumption;

  /**
   * If {@link DDBItem#author} is null then the author is anonymous.
   * @param metaData the metadata of the item.
   * @return Answer the answer.
   */
  @SuppressWarnings("javadoc")
  @Override
  public Answer answerQuestionForItem(DDBItem metaData) {
    if (metaData.getAuthors().isEmpty()) {
      this.assumption = null;
      return Answer.YES;
    } else {
      this.assumption = "Assumed no, because no author is known.";
      return Answer.ASSUMED_NO;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getAssumptionForLastAnswer() {
    return this.assumption;
  }
}
