package de.ddb.pdc.answerer.answerers;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import de.ddb.pdc.answerer.Answerer;
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
    Calendar death = new GregorianCalendar();
    death.set(Calendar.YEAR, currentYear);
    Author author = new Author("test-id", null, null, null, death, null);
    metadata.addAuthor(author);

    Answerer answerer = new AuthorDiedMoreThan70YearsAgoAnswerer();
    Answer answer = answerer.answerQuestionForItem(metadata);

    assertEquals(Answer.NO, answer);
  }

  @Test
  public void Year69Test() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata = new DDBItem("test-id");
    Calendar death = new GregorianCalendar();
    death.set(Calendar.YEAR, currentYear-69);
    Author author = new Author("test-id", null, null, null, death, null);
    metadata.addAuthor(author);

    Answerer answerer = new AuthorDiedMoreThan70YearsAgoAnswerer();
    Answer answer = answerer.answerQuestionForItem(metadata);

    assertEquals(Answer.NO, answer);
  }

  @Test
  public void Year70Test() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata = new DDBItem("test-id");
    Calendar death = new GregorianCalendar();
    death.set(Calendar.YEAR, currentYear-70);
    Author author = new Author("test-id", null, null, null, death, null);
    metadata.addAuthor(author);

    Answerer answerer = new AuthorDiedMoreThan70YearsAgoAnswerer();
    Answer answer = answerer.answerQuestionForItem(metadata);

    assertEquals(Answer.NO, answer);
  }

  @Test
  public void Year71Test() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata = new DDBItem("test-id");
    Calendar death = new GregorianCalendar();
    death.set(Calendar.YEAR, currentYear-71);
    Author author = new Author("test-id", null, null, null, death, null);
    metadata.addAuthor(author);

    Answerer answerer = new AuthorDiedMoreThan70YearsAgoAnswerer();
    Answer answer = answerer.answerQuestionForItem(metadata);

    assertEquals(Answer.YES, answer);
  }

  @Test
  public void MultipleAuthorPositiveTest() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata = new DDBItem("test-id");
    Calendar death = new GregorianCalendar();
    death.set(Calendar.YEAR, currentYear-71);
    Author author = new Author("test-id", null, null, null, death, null);
    metadata.addAuthor(author);
    metadata.addAuthor(author);
    metadata.addAuthor(author);

    Answerer answerer = new AuthorDiedMoreThan70YearsAgoAnswerer();
    Answer answer = answerer.answerQuestionForItem(metadata);

    assertEquals(Answer.YES, answer);
  }

  @Test
  public void MultipleAuthorNegativeTest() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata = new DDBItem("test-id");
    Calendar death = new GregorianCalendar();
    death.set(Calendar.YEAR, currentYear-71);
    Author author = new Author("test-id", null, null, null, death, null);
    metadata.addAuthor(author);

    death = new GregorianCalendar();
    death.set(Calendar.YEAR, currentYear-69);
    author = new Author("test-id", null, null, null, death, null);
    metadata.addAuthor(author);

    Answerer answerer = new AuthorDiedMoreThan70YearsAgoAnswerer();
    Answer answer = answerer.answerQuestionForItem(metadata);

    assertEquals(Answer.NO, answer);
  }

}
