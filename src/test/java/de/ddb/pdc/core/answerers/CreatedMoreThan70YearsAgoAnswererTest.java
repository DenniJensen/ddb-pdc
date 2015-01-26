package de.ddb.pdc.core.answerers;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import de.ddb.pdc.metadata.DdbTimeSpan;
import org.junit.Test;

import de.ddb.pdc.core.Answerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

@SuppressWarnings({"static-method", "javadoc", "nls"})
public class CreatedMoreThan70YearsAgoAnswererTest {

  @Test
  public void currentYearTest() {
    DDBItem metadata = new DDBItem("test-id");
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    metadata.setPublishingYearRange(new DdbTimeSpan(currentYear));

    Answerer answerer = new CreatedMoreThan70YearsAgoAnswerer();
    Answer answer = answerer.answerQuestionForItem(metadata);

    assertEquals(Answer.NO, answer);
  }

  @Test
  public void Year69Test() {
    DDBItem metadata = new DDBItem("test-id");
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    metadata.setPublishingYearRange(new DdbTimeSpan(currentYear - 69));


    Answerer answerer = new CreatedMoreThan70YearsAgoAnswerer();
    Answer answer = answerer.answerQuestionForItem(metadata);

    assertEquals(Answer.NO, answer);
  }

  @Test
  public void year70Test() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata = new DDBItem("test-id");
    metadata.setPublishingYearRange(new DdbTimeSpan(currentYear - 70));


    Answerer answerer = new CreatedMoreThan70YearsAgoAnswerer();
    Answer answer = answerer.answerQuestionForItem(metadata);

    assertEquals(Answer.NO, answer);
  }

  @Test
  public void year71Test() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata = new DDBItem("test-id");
    metadata.setPublishingYearRange(new DdbTimeSpan(currentYear - 71));


    Answerer answerer = new CreatedMoreThan70YearsAgoAnswerer();
    Answer answer = answerer.answerQuestionForItem(metadata);

    assertEquals(Answer.YES, answer);
  }

}
