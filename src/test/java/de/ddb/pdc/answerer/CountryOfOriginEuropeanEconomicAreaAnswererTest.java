package de.ddb.pdc.answerer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.ddb.pdc.answerer.answerers.CountryOfOriginEuropeanEconomicAreaAnswerer;
import de.ddb.pdc.answerer.answerers.EEAMembers;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.Author;
import de.ddb.pdc.metadata.DDBItem;

@SuppressWarnings({"static-method", "javadoc", "nls"})
public class CountryOfOriginEuropeanEconomicAreaAnswererTest {

  @Test
  public void memberTest() {

    for (EEAMembers member : EEAMembers.values()) {
      DDBItem metadata = new DDBItem("test-id");
      // FIXME wrong nationality
      Author author = new Author("test-id", null, null, null, null,
          member.name());
      metadata.addAuthor(author);

      Answerer answerer = new CountryOfOriginEuropeanEconomicAreaAnswerer();
      Answer answer = answerer.getAnswer(metadata);

      assertEquals(Answer.YES, answer);
    }
  }

  @Test
  public void notMemberTest() {

    DDBItem metadata = new DDBItem("test-id");
    // FIXME wrong nationality
    Author author = new Author("test-id", null, null, null, null,
        "russia");
    metadata.addAuthor(author);

    Answerer answerer = new CountryOfOriginEuropeanEconomicAreaAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    assertEquals(Answer.NO, answer);
  }

}
