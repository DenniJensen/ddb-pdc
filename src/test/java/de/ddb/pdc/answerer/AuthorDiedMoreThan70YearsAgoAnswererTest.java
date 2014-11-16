package de.ddb.pdc.answerer;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import de.ddb.pdc.answerer.answerers.AuthorDiedMoreThan70YearsAgoAnswerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

@SuppressWarnings({"static-method", "javadoc"})
public class AuthorDiedMoreThan70YearsAgoAnswererTest {

  @Test
  public void currentYearTest() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata =
        new DDBItem(null, null, null, 0, 0, null, 0, currentYear, null);

    Answerer answerer = new AuthorDiedMoreThan70YearsAgoAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    Assert.assertEquals(Answer.NO, answer);
  }

  @Test
  public void Year69Test() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata =
        new DDBItem(null, null, null, 0, 0, null, 0, currentYear - 69, null);

    Answerer answerer = new AuthorDiedMoreThan70YearsAgoAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    Assert.assertEquals(Answer.NO, answer);
  }

  @Test
  public void Year70Test() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata =
        new DDBItem(null, null, null, 0, 0, null, 0, currentYear - 70, null);

    Answerer answerer = new AuthorDiedMoreThan70YearsAgoAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    Assert.assertEquals(Answer.NO, answer);
  }

  @Test
  public void Year71Test() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata =
        new DDBItem(null, null, null, 0, 0, null, 0, currentYear - 71, null);

    Answerer answerer = new AuthorDiedMoreThan70YearsAgoAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    Assert.assertEquals(Answer.YES, answer);
  }

}
