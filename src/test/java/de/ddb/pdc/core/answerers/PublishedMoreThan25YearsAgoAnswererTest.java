package de.ddb.pdc.core.answerers;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import de.ddb.pdc.metadata.DdbTimeSpan;
import org.junit.Test;

import de.ddb.pdc.core.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

@SuppressWarnings({"static-method", "javadoc", "nls"})
public class PublishedMoreThan25YearsAgoAnswererTest {

  @Test
  public void currentYearTest() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata = new DDBItem("test-id");
    metadata.setPublishingYearRange(new DdbTimeSpan(currentYear));

    Answerer answerer = new PublishedMoreThan25YearsAgoAnswerer();
    Answer answer = answerer.answerQuestionForItem(metadata);

    assertEquals(Answer.NO, answer);
  }

  @Test
  public void year24Test() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata = new DDBItem("test-id");
    metadata.setPublishingYearRange(new DdbTimeSpan(currentYear - 24));

    Answerer answerer = new PublishedMoreThan25YearsAgoAnswerer();
    Answer answer = answerer.answerQuestionForItem(metadata);

    assertEquals(Answer.NO, answer);
  }

  @Test
  public void year25Test() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata = new DDBItem("test-id");
    metadata.setPublishingYearRange(new DdbTimeSpan(currentYear - 25));

    Answerer answerer = new PublishedMoreThan25YearsAgoAnswerer();
    Answer answer = answerer.answerQuestionForItem(metadata);

    assertEquals(Answer.NO, answer);
  }

  @Test
  public void year26Test() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata = new DDBItem("test-id");
    metadata.setPublishingYearRange(new DdbTimeSpan(currentYear - 26));

    Answerer answerer = new PublishedMoreThan25YearsAgoAnswerer();
    Answer answer = answerer.answerQuestionForItem(metadata);

    assertEquals(Answer.YES, answer);
  }

}
