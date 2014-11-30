package de.ddb.pdc.core.answerers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.ddb.pdc.core.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

@SuppressWarnings({"static-method", "javadoc", "nls"})
public class WorkPublishedOrCommunicatedAnswererTest {

  @Test
  public void test() {
    DDBItem metadata = new DDBItem("test-id");

    Answerer answerer = new WorkPublishedOrCommunicatedAnswerer();
    Answer answer = answerer.answerQuestionForItem(metadata);

    // FIXME Hardcoded
    assertEquals(Answer.ASSUMED_YES, answer);
  }

}
