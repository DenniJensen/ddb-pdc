package de.ddb.pdc.core;

import de.ddb.pdc.core.answerers.AnswererFactory;
import de.ddb.pdc.metadata.Author;
import de.ddb.pdc.metadata.DDBItem;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;

public class PublicDomainCalculatorImplTest {

  @Test
  public void calculateTest(){
    QuestionnaireFactory questionaireFactory = new QuestionnaireFactory();
    AnswererFactory answererFactory = new AnswererFactory();
    PublicDomainCalculatorImpl publicDomainCalculatorIml =
            new PublicDomainCalculatorImpl(questionaireFactory,answererFactory);

    DDBItem ddbItemA = new DDBItem("123");
    ddbItemA.setTitle("Dummy Title");
    Calendar birthYearA = new GregorianCalendar();
    birthYearA.set(1920, 10, 10);
    Calendar deathYearA = new GregorianCalendar();
    deathYearA.set(1995, 10, 10);
    Author authorA = new Author("321", "Dummy Author", birthYearA, "GERMANY",
            deathYearA, "GERMANY");
    ddbItemA.addAuthor(authorA);
    ddbItemA.setPublishedYear(1955);

    PDCResult resultA = publicDomainCalculatorIml.calculate("de", ddbItemA);
    // because AUTHOR_DIED_MORE_THAN_70_YEARS_AGO is NO
    assertFalse(resultA.isPublicDomain());

    DDBItem ddbItemB = new DDBItem("123");
    ddbItemB.setTitle("Dummy Title");
    Calendar birthYearB = new GregorianCalendar();
    birthYearB.set(1880, 10, 10);
    Calendar deathYearB = new GregorianCalendar();
    deathYearB.set(1925, 10, 10);
    Author authorB = new Author("321", "Dummy Author", birthYearB, "GERMANY",
            deathYearB, "GERMANY");
    ddbItemB.addAuthor(authorB);
    ddbItemB.setPublishedYear(1925);

    PDCResult resultB = publicDomainCalculatorIml.calculate("de", ddbItemB);
    // because AUTHOR_DIED_MORE_THAN_70_YEARS_AGO is YES
    assertTrue(resultB.isPublicDomain());
  }
}
