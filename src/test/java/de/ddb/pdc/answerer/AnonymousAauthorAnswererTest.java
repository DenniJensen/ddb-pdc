package de.ddb.pdc.answerer;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import de.ddb.pdc.answerer.answerers.AnonymousAauthorAnswerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.Author;
import de.ddb.pdc.metadata.DDBItem;

@SuppressWarnings({"static-method", "javadoc", "nls"})
public class AnonymousAauthorAnswererTest {

  @Test
  public void authorAnonymousTest() {
    DDBItem metadata = new DDBItem("test-id");

    Answerer answerer = new AnonymousAauthorAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    Assert.assertEquals(Answer.YES, answer);
  }

  @Test
  public void authorNotAnonymousTest() {
    Author author = new Author("test-id", "Goethe", null, null, null, null);
    DDBItem metadata = new DDBItem("test-id");
    metadata.addAuthor(author);

    Answerer answerer = new AnonymousAauthorAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    assertEquals(Answer.NO, answer);
  }

}
