package de.ddb.pdc.core.answerers;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import de.ddb.pdc.metadata.DdbTimeSpan;
import org.junit.Test;

import de.ddb.pdc.core.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.Author;
import de.ddb.pdc.metadata.DDBItem;

@SuppressWarnings({"static-method", "javadoc", "nls"})
public class PublishedWithin70YearsOfDeathAnswererTest {

  @Test
  public void lessThan70YearsTest() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata = new DDBItem("test-id");
    metadata.setPublishingYearRange(new DdbTimeSpan(currentYear));

    Calendar death = new GregorianCalendar();
    death.set(Calendar.YEAR, currentYear);
    Author author = new Author("test-id", null, null, null, death, null);
    metadata.addAuthor(author);

    Answerer answerer = new PublishedWithin70YearsOfDeathAnswerer();
    Answer answer = answerer.answerQuestionForItem(metadata);

    assertEquals(Answer.YES, answer);
  }

  @Test
  public void exactly70YearsTest() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata = new DDBItem("test-id");
    metadata.setPublishingYearRange(new DdbTimeSpan(currentYear));

    Calendar death = new GregorianCalendar();
    death.set(Calendar.YEAR, currentYear-70);
    Author author = new Author("test-id", null, null, null, death, null);
    metadata.addAuthor(author);

    Answerer answerer = new PublishedWithin70YearsOfDeathAnswerer();
    Answer answer = answerer.answerQuestionForItem(metadata);

    assertEquals(Answer.YES, answer);
  }

  @Test
  public void moreThan70YearsTest() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata = new DDBItem("test-id");
    metadata.setPublishingYearRange(new DdbTimeSpan(currentYear));

    Calendar death = new GregorianCalendar();
    death.set(Calendar.YEAR, currentYear-71);
    Author author = new Author("test-id", null, null, null, death, null);
    metadata.addAuthor(author);

    Answerer answerer = new PublishedWithin70YearsOfDeathAnswerer();
    Answer answer = answerer.answerQuestionForItem(metadata);

    assertEquals(Answer.NO, answer);
  }

}
