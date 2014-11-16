package de.ddb.pdc.answerer;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import de.ddb.pdc.answerer.answerers.CreatedMoreThan70YearsAgoAnswerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

@SuppressWarnings({"static-method", "javadoc"})
public class CreatedMoreThan70YearsAgoAnswererTest {

  @Test
  public void currentYearTest() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata =
        new DDBItem(null, null, null, currentYear, 0, null, 0, 0, null);

    Answerer answerer = new CreatedMoreThan70YearsAgoAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    Assert.assertEquals(Answer.NO, answer);
  }

  @Test
  public void Year69Test() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata =
        new DDBItem(null, null, null, currentYear - 69, 0, null, 0, 0, null);

    Answerer answerer = new CreatedMoreThan70YearsAgoAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    Assert.assertEquals(Answer.NO, answer);
  }

  @Test
  public void year70Test() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata =
        new DDBItem(null, null, null, currentYear - 70, 0, null, 0, 0, null);

    Answerer answerer = new CreatedMoreThan70YearsAgoAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    Assert.assertEquals(Answer.NO, answer);
  }

  @Test
  public void year71Test() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata =
        new DDBItem(null, null, null, currentYear - 71, 0, null, 0, 0, null);

    Answerer answerer = new CreatedMoreThan70YearsAgoAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    Assert.assertEquals(Answer.YES, answer);
  }

}
