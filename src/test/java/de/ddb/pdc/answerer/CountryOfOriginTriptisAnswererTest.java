package de.ddb.pdc.answerer;

import org.junit.Assert;
import org.junit.Test;

import de.ddb.pdc.answerer.answerers.CountryOfOriginTriptisAnswerer;
import de.ddb.pdc.core.Answer;
import de.ddb.pdc.metadata.DDBItem;

@SuppressWarnings({"static-method", "javadoc"})
public class CountryOfOriginTriptisAnswererTest {

  @Test
  public void test() {
    DDBItem metadata =
        new DDBItem(null, null, null, 0, 0, null, 0, 0, null);

    Answerer answerer = new CountryOfOriginTriptisAnswerer();
    Answer answer = answerer.getAnswer(metadata);

    // FIXME Hardcoded
    Assert.assertEquals(Answer.YES, answer);
  }

}
