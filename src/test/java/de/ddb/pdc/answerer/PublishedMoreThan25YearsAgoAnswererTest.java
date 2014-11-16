package de.ddb.pdc.answerer;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;

import de.ddb.pdc.answerer.answerers.PublishedMoreThan25YearsAgoAnswerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

@SuppressWarnings({"static-method", "javadoc"})
public class PublishedMoreThan25YearsAgoAnswererTest {

  @Test
  public void currentYearTest() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata =
        new DDBItem(null, null, null, 0, currentYear, null, 0, 0, null);

    Answerer answerer = new PublishedMoreThan25YearsAgoAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    assertEquals(Answer.NO, answer);
  }

  @Test
  public void year24Test() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata =
        new DDBItem(null, null, null, 0, currentYear - 24, null, 0, 0, null);

    Answerer answerer = new PublishedMoreThan25YearsAgoAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    assertEquals(Answer.NO, answer);
  }

  @Test
  public void year25Test() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata =
        new DDBItem(null, null, null, 0, currentYear - 25, null, 0, 0, null);

    Answerer answerer = new PublishedMoreThan25YearsAgoAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    assertEquals(Answer.NO, answer);
  }

  @Test
  public void year26Test() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata =
        new DDBItem(null, null, null, 0, currentYear - 26, null, 0, 0, null);

    Answerer answerer = new PublishedMoreThan25YearsAgoAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    assertEquals(Answer.YES, answer);
  }

}
