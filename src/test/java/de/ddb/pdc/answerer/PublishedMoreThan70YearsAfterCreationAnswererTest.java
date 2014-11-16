package de.ddb.pdc.answerer;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import de.ddb.pdc.answerer.answerers.PublishedMoreThan70YearsAfterCreationAnswerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

@SuppressWarnings({"static-method", "javadoc"})
public class PublishedMoreThan70YearsAfterCreationAnswererTest {

  @Test
  public void lessThan70YearsTest() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata =
        new DDBItem(null, null, null, currentYear, currentYear, null, 0, 0,
            null);

    Answerer answerer = new PublishedMoreThan70YearsAfterCreationAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    Assert.assertEquals(Answer.NO, answer);
  }

  @Test
  public void exactly70YearsTest() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata =
        new DDBItem(null, null, null, currentYear - 70, currentYear, null, 0,
            0, null);

    Answerer answerer = new PublishedMoreThan70YearsAfterCreationAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    Assert.assertEquals(Answer.NO, answer);
  }

  @Test
  public void moreThan70YearsTest() {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    DDBItem metadata =
        new DDBItem(null, null, null, currentYear - 71, currentYear, null, 0,
            0, null);

    Answerer answerer = new PublishedMoreThan70YearsAfterCreationAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    Assert.assertEquals(Answer.YES, answer);
  }

}
