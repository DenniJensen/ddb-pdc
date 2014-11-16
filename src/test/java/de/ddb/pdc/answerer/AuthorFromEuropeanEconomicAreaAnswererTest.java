package de.ddb.pdc.answerer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.ddb.pdc.answerer.answerers.AuthorFromEuropeanEconomicAreaAnswerer;
import de.ddb.pdc.answerer.answerers.EEAMembers;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.Author;
import de.ddb.pdc.metadata.DDBItem;

@SuppressWarnings({"static-method", "javadoc", "nls"})
public class AuthorFromEuropeanEconomicAreaAnswererTest {

  @Test
  public void memberTest() {

    for (EEAMembers member : EEAMembers.values()) {
      DDBItem metadata = new DDBItem("test-id");
      Author author = new Author("test-id");
      author.setNationality(member.name());
      metadata.setAuthor(author);

      Answerer answerer = new AuthorFromEuropeanEconomicAreaAnswerer();
      Answer answer = answerer.getAnswer(metadata);

      assertEquals(Answer.YES, answer);
    }
  }

  @Test
  public void notMemberTest() {

    DDBItem metadata = new DDBItem("test-id");
    Author author = new Author("test-id");
    author.setNationality("russia");
    metadata.setAuthor(author);

    Answerer answerer = new AuthorFromEuropeanEconomicAreaAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    assertEquals(Answer.NO, answer);
  }

}
