package de.ddb.pdc.answerer.answerers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.ddb.pdc.answerer.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

@SuppressWarnings({"static-method", "javadoc", "nls"})
public class CountryOfOriginTriptisAnswererTest {

  @Test
  public void test() {
    DDBItem metadata = new DDBItem("test-id");

    Answerer answerer = new CountryOfOriginTriptisAnswerer();
    Answer answer = answerer.answerQuestionForItem(metadata);

    // FIXME Hardcoded
    assertEquals(Answer.ASSUMED_YES, answer);
  }

}
