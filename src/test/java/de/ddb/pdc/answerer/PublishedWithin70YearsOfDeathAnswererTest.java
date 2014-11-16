package de.ddb.pdc.answerer;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import de.ddb.pdc.answerer.answerers.PublishedWithin70YearsOfDeathAnswerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

@SuppressWarnings({"static-method", "javadoc"})
public class PublishedWithin70YearsOfDeathAnswererTest {

  @Test
  public void lessThan70YearsTest() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata =
        new DDBItem(null, null, null, 0, currentYear, null, 0, currentYear,
            null);

    Answerer answerer = new PublishedWithin70YearsOfDeathAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    Assert.assertEquals(Answer.YES, answer);
  }

  @Test
  public void exactly70YearsTest() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata =
        new DDBItem(null, null, null, 0, currentYear, null, 0,
            currentYear - 70, null);

    Answerer answerer = new PublishedWithin70YearsOfDeathAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    Assert.assertEquals(Answer.YES, answer);
  }

  @Test
  public void moreThan70YearsTest() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata =
        new DDBItem(null, null, null, 0, currentYear, null, 0,
            currentYear - 71, null);

    Answerer answerer = new PublishedWithin70YearsOfDeathAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    Assert.assertEquals(Answer.NO, answer);
  }

}
