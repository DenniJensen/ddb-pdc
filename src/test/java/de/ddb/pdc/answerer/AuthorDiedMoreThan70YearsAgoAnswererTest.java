package de.ddb.pdc.answerer;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;

import de.ddb.pdc.answerer.answerers.AuthorDiedMoreThan70YearsAgoAnswerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.Author;
import de.ddb.pdc.metadata.DDBItem;

@SuppressWarnings({"static-method", "javadoc", "nls"})
public class AuthorDiedMoreThan70YearsAgoAnswererTest {

  @Test
  public void currentYearTest() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata = new DDBItem("test-id");
    Author author = new Author("test-id");
    author.setDeathYear(currentYear);
    metadata.setAuthor(author);

    Answerer answerer = new AuthorDiedMoreThan70YearsAgoAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    assertEquals(Answer.NO, answer);
  }

  @Test
  public void Year69Test() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata = new DDBItem("test-id");
    Author author = new Author("test-id");
    author.setDeathYear(currentYear-69);
    metadata.setAuthor(author);

    Answerer answerer = new AuthorDiedMoreThan70YearsAgoAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    assertEquals(Answer.NO, answer);
  }

  @Test
  public void Year70Test() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata = new DDBItem("test-id");
    Author author = new Author("test-id");
    author.setDeathYear(currentYear-70);
    metadata.setAuthor(author);

    Answerer answerer = new AuthorDiedMoreThan70YearsAgoAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    assertEquals(Answer.NO, answer);
  }

  @Test
  public void Year71Test() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata = new DDBItem("test-id");
    Author author = new Author("test-id");
    author.setDeathYear(currentYear-71);
    metadata.setAuthor(author);

    Answerer answerer = new AuthorDiedMoreThan70YearsAgoAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    assertEquals(Answer.YES, answer);
  }

  @Test
  public void MultipleAuthorPositiveTest() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata = new DDBItem("test-id");
    Author author = new Author("test-id");
    author.setDeathYear(currentYear-71);
    metadata.setAuthor(author);
    metadata.setAuthor(author);
    metadata.setAuthor(author);

    Answerer answerer = new AuthorDiedMoreThan70YearsAgoAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    assertEquals(Answer.YES, answer);
  }

  @Test
  public void MultipleAuthorNegativeTest() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata = new DDBItem("test-id");
    Author author = new Author("test-id");
    author.setDeathYear(currentYear-71);
    metadata.setAuthor(author);
    author = new Author("test-id");
    author.setDeathYear(currentYear-69);
    metadata.setAuthor(author);

    Answerer answerer = new AuthorDiedMoreThan70YearsAgoAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    assertEquals(Answer.NO, answer);
  }

}
