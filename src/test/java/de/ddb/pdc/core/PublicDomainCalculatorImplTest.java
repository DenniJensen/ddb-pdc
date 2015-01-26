package de.ddb.pdc.core;

import de.ddb.pdc.core.answerers.AnswererFactory;
import de.ddb.pdc.metadata.Author;
import de.ddb.pdc.metadata.DDBItem;
import java.util.Calendar;
import java.util.GregorianCalendar;

import de.ddb.pdc.metadata.DdbTimeSpan;
import org.junit.Test;
import static org.junit.Assert.*;

public class PublicDomainCalculatorImplTest {

  @Test
  public void calculateYes() {
    QuestionnaireFactory questionaireFactory = new QuestionnaireFactory();
    AnswererFactory answererFactory = new AnswererFactory();
    PublicDomainCalculatorImpl publicDomainCalculatorImlp =
        new PublicDomainCalculatorImpl(questionaireFactory,answererFactory);

    DDBItem ddbItem = new DDBItem("123");
    ddbItem.setTitle("Dummy Title");
    Calendar birthYear = new GregorianCalendar();
    birthYear.set(1880, 10, 10);
    Calendar deathYear = new GregorianCalendar();
    deathYear.set(1925, 10, 10);
    Author author = new Author("321", "Dummy Author", birthYear, "GERMANY",
        deathYear, "GERMANY");
    ddbItem.addAuthor(author);
    ddbItem.setPublishingYearRange(new DdbTimeSpan(1925));

    PDCResult result = publicDomainCalculatorImlp.calculate("de", ddbItem);
    // because AUTHOR_DIED_MORE_THAN_70_YEARS_AGO is YES
    assertTrue(result.isPublicDomain());
  }

  @Test
  public void calculateNo() {
    QuestionnaireFactory questionaireFactory = new QuestionnaireFactory();
    AnswererFactory answererFactory = new AnswererFactory();
    PublicDomainCalculatorImpl publicDomainCalculatorImlp =
        new PublicDomainCalculatorImpl(questionaireFactory,answererFactory);

    DDBItem ddbItem = new DDBItem("123");
    ddbItem.setTitle("Dummy Title");
    Calendar birthYear = new GregorianCalendar();
    birthYear.set(1920, 10, 10);
    Calendar deathYear = new GregorianCalendar();
    deathYear.set(1995, 10, 10);
    Author author = new Author("321", "Dummy Author", birthYear, "GERMANY",
        deathYear, "GERMANY");
    ddbItem.addAuthor(author);
    ddbItem.setPublishingYearRange(new DdbTimeSpan(1955));

    PDCResult result = publicDomainCalculatorImlp.calculate("de", ddbItem);
    // because AUTHOR_DIED_MORE_THAN_70_YEARS_AGO is NO
    assertFalse(result.isPublicDomain());
  }

  @Test
  public void calculateUnknown() {
    QuestionnaireFactory questionaireFactory = new QuestionnaireFactory();
    AnswererFactory answererFactory = new AnswererFactory();
    PublicDomainCalculatorImpl publicDomainCalculatorImlp =
        new PublicDomainCalculatorImpl(questionaireFactory,answererFactory);

    DDBItem ddbItem = new DDBItem("123");
    ddbItem.setTitle("Dummy Title");
    Calendar birthYear = new GregorianCalendar();
    birthYear.set(1920, 10, 10);
    Calendar deathYear = new GregorianCalendar();
    deathYear.set(1995, 10, 10);
    //Author author = new Author("321", "Dummy Author", birthYear, "GERMANY",
    //    deathYear, "CHINA");
    //ddbItem.addAuthor(author);
    ddbItem.setPublishingYearRange(new DdbTimeSpan(1955));
    ddbItem.setPublishingYearRange(new DdbTimeSpan(1955));

    PDCResult result = publicDomainCalculatorImlp.calculate("de", ddbItem);
    // because No Author is known
    assertNull(result.isPublicDomain());
  }
}
