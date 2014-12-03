package de.ddb.pdc.core.answerers;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import de.ddb.pdc.core.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.Author;
import de.ddb.pdc.metadata.DDBItem;

@SuppressWarnings({"static-method", "javadoc", "nls"})
public class AnonymousAuthorAnswererTest {

  @Test
  public void authorAnonymousTest() {
    DDBItem metadata = new DDBItem("test-id");

    Answerer answerer = new AnonymousAuthorAnswerer();
    Answer answer = answerer.answerQuestionForItem(metadata);

    Assert.assertEquals(Answer.YES, answer);
  }

  @Test
  public void authorNotAnonymousTest() {
    Author author = new Author("test-id", "Goethe", null, null, null, null);
    DDBItem metadata = new DDBItem("test-id");
    metadata.addAuthor(author);

    Answerer answerer = new AnonymousAuthorAnswerer();
    Answer answer = answerer.answerQuestionForItem(metadata);

    assertEquals(Answer.NO, answer);
  }

}
