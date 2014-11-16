package de.ddb.pdc.answerer;

import org.junit.Assert;
import org.junit.Test;

import de.ddb.pdc.answerer.answerers.AnonymousAauthorAnswerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

@SuppressWarnings({"static-method", "javadoc", "nls"})
public class AnonymousAauthorAnswererTest {

  @Test
  public void authorAnonymousTest() {
    DDBItem metadata =
        new DDBItem(null, null, null, 0, 0, null, 0, 0, null);

    Answerer answerer = new AnonymousAauthorAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    Assert.assertEquals(Answer.YES, answer);
  }

  @Test
  public void authorNotAnonymousTest() {
    DDBItem metadata =
        new DDBItem(null, null, null, 0, 0, "Goethe", 0, 0, null);

    Answerer answerer = new AnonymousAauthorAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    Assert.assertEquals(Answer.NO, answer);
  }

}
