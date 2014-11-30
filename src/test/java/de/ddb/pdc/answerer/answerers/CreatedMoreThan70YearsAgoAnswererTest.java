package de.ddb.pdc.answerer.answerers;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;

import de.ddb.pdc.answerer.Answerer;
import de.ddb.pdc.answerer.answerers.CreatedMoreThan70YearsAgoAnswerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

@SuppressWarnings({"static-method", "javadoc", "nls"})
public class CreatedMoreThan70YearsAgoAnswererTest {

  @Test
  public void currentYearTest() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata = new DDBItem("test-id");
    metadata.setPublishedYear(currentYear);

    Answerer answerer = new CreatedMoreThan70YearsAgoAnswerer();
    Answer answer = answerer.answerQuestionForItem(metadata);

    assertEquals(Answer.NO, answer);
  }

  @Test
  public void Year69Test() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata = new DDBItem("test-id");
    metadata.setPublishedYear(currentYear-69);


    Answerer answerer = new CreatedMoreThan70YearsAgoAnswerer();
    Answer answer = answerer.answerQuestionForItem(metadata);

    assertEquals(Answer.NO, answer);
  }

  @Test
  public void year70Test() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata = new DDBItem("test-id");
    metadata.setPublishedYear(currentYear-70);


    Answerer answerer = new CreatedMoreThan70YearsAgoAnswerer();
    Answer answer = answerer.answerQuestionForItem(metadata);

    assertEquals(Answer.NO, answer);
  }

  @Test
  public void year71Test() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata = new DDBItem("test-id");
    metadata.setPublishedYear(currentYear-71);


    Answerer answerer = new CreatedMoreThan70YearsAgoAnswerer();
    Answer answer = answerer.answerQuestionForItem(metadata);

    assertEquals(Answer.YES, answer);
  }

}
