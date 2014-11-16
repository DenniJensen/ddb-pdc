package de.ddb.pdc.answerer;

import org.junit.Assert;
import org.junit.Test;

import de.ddb.pdc.answerer.answerers.AnswererImplCOOE;
import de.ddb.pdc.answerer.answerers.EEAMembers;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

@SuppressWarnings({"static-method", "javadoc", "nls"})
public class AnswererImplCOOETest {

  @Test
  public void memberTest() {

    for (EEAMembers member : EEAMembers.values()) {
      DDBItem metadata =
          new DDBItem(null, null, member.name(), 0, 0, null, 0, 0, null);

      Answerer answerer = new AnswererImplCOOE();
      Answer answer = answerer.getAnswer(metadata);

      Assert.assertEquals(Answer.YES, answer);
    }
  }

  @Test
  public void notMemberTest() {

    DDBItem metadata =
        new DDBItem(null, null, "russia", 0, 0, null, 0, 0, null);

    Answerer answerer = new AnswererImplCOOE();
    Answer answer = answerer.getAnswer(metadata);

    Assert.assertEquals(Answer.NO, answer);
  }

}
