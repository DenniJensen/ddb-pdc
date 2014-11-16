package de.ddb.pdc.answerer;

import org.junit.Assert;
import org.junit.Test;

import de.ddb.pdc.answerer.answerers.AuthorFromEuropeanEconomicAreaAnswerer;
import de.ddb.pdc.answerer.answerers.EEAMembers;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

@SuppressWarnings({"static-method", "javadoc", "nls"})
public class AuthorFromEuropeanEconomicAreaAnswererTest {

  @Test
  public void memberTest() {

    for (EEAMembers member : EEAMembers.values()) {
      DDBItem metadata =
          new DDBItem(null, null, null, 0, 0, null, 0, 0, member.name());

      Answerer answerer = new AuthorFromEuropeanEconomicAreaAnswerer();
      Answer answer = answerer.getAnswer(metadata);

      Assert.assertEquals(Answer.YES, answer);
    }
  }

  @Test
  public void notMemberTest() {

    DDBItem metadata =
        new DDBItem(null, null, null, 0, 0, null, 0, 0, "russia");

    Answerer answerer = new AuthorFromEuropeanEconomicAreaAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    Assert.assertEquals(Answer.NO, answer);
  }

}
