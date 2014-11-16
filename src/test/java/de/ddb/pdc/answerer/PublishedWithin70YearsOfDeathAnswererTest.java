package de.ddb.pdc.answerer;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;

import de.ddb.pdc.answerer.answerers.PublishedWithin70YearsOfDeathAnswerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.Author;
import de.ddb.pdc.metadata.DDBItem;

@SuppressWarnings({"static-method", "javadoc", "nls"})
public class PublishedWithin70YearsOfDeathAnswererTest {

  @Test
  public void lessThan70YearsTest() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata = new DDBItem("test-id");
    metadata.setPublishedYear(currentYear);
    Author author = new Author("test-id");
    author.setDeathYear(currentYear);
    metadata.setAuthor(author);

    Answerer answerer = new PublishedWithin70YearsOfDeathAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    assertEquals(Answer.YES, answer);
  }

  @Test
  public void exactly70YearsTest() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata = new DDBItem("test-id");
    metadata.setPublishedYear(currentYear);
    Author author = new Author("test-id");
    author.setDeathYear(currentYear-70);
    metadata.setAuthor(author);

    Answerer answerer = new PublishedWithin70YearsOfDeathAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    assertEquals(Answer.YES, answer);
  }

  @Test
  public void moreThan70YearsTest() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata = new DDBItem("test-id");
    metadata.setPublishedYear(currentYear);
    Author author = new Author("test-id");
    author.setDeathYear(currentYear-71);
    metadata.setAuthor(author);

    Answerer answerer = new PublishedWithin70YearsOfDeathAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    assertEquals(Answer.NO, answer);
  }

}
