package de.ddb.pdc.answerer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.ddb.pdc.answerer.answerers.AuthorFromTriptisAnswerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

@SuppressWarnings({"static-method", "javadoc"})
public class AuthorFromTriptisAnswererTest {

  @Test
  public void test() {
    DDBItem metadata =
        new DDBItem(null, null, null, 0, 0, null, 0, 0, null);

    Answerer answerer = new AuthorFromTriptisAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    // FIXME Hardcoded
    assertEquals(Answer.YES, answer);
  }

}
