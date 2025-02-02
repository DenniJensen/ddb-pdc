package de.ddb.pdc.core.answerers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.ddb.pdc.core.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.Author;
import de.ddb.pdc.metadata.DDBItem;

@SuppressWarnings({"static-method", "javadoc", "nls"})
public class AuthorFromEuropeanEconomicAreaAnswererTest {

  @Test
  public void memberTest() {

    for (EEAMembers member : EEAMembers.values()) {
      DDBItem metadata = new DDBItem("test-id");
      Author author = new Author("test-id", null, null, null, null, member.name());
      metadata.addAuthor(author);

      Answerer answerer = new AuthorFromEuropeanEconomicAreaAnswerer();
      Answer answer = answerer.answerQuestionForItem(metadata);

      assertEquals(Answer.YES, answer);
    }
  }

  @Test
  public void notMemberTest() {

    DDBItem metadata = new DDBItem("test-id");
    Author author = new Author("test-id", null, null, null, null, "russia");
    metadata.addAuthor(author);

    Answerer answerer = new AuthorFromEuropeanEconomicAreaAnswerer();
    Answer answer = answerer.answerQuestionForItem(metadata);

    assertEquals(Answer.NO, answer);
  }

}
