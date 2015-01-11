package de.ddb.pdc.metadata;

import org.springframework.xml.xpath.XPathOperations;

import javax.xml.transform.dom.DOMSource;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Class to operating with xpath on the DNB author request xml.
 */
public class ItemDnbAuthorXml {

  private DOMSource domSource;
  private XPathOperations xpath;

  public ItemDnbAuthorXml(DOMSource domSource, XPathOperations xpath) {
    this.domSource = domSource;
    this.xpath = xpath;
  }

  /**
   * Returns name of the author.
   */
  public String getName() {
    return xpath.evaluateAsString("//gndo:variantNameForThePerson", domSource);
  }

  /**
   * Returns a calendar object with date of birth.
   */
  public Calendar getDateOfBirth() {
    String dateOfBirth = xpath.evaluateAsString("//gndo:dateOfBirth", domSource);
    return formatStringToDate(dateOfBirth);
  }

  /**
   * Returns a calendar object with date of death.
   */
  public Calendar getDateOfDeath() {
    String dateOfDeath = xpath.evaluateAsString("//gndo:dateOfDeath", domSource);
    return formatStringToDate(dateOfDeath);
  }

  /**
   * Returns DNB URL to place of birth.
   */
  public String getPlaceOfBirth() {
    return xpath.evaluateAsString("//gndo:placeOfBirth/rdf:Description/@rdf:about", domSource);
  }

  /**
   * Returns DNB URL to place of death.
   */
  public String getPlaceOfDeath() {
    return xpath.evaluateAsString("//gndo:placeOfDeath/rdf:Description/@rdf:about", domSource);
  }

  private Calendar formatStringToDate(String date) {
    String[] sections = date.split("-");
    if (sections.length == 3) {
      int year = Integer.parseInt(sections[0]);
      int month = Integer.parseInt(sections[1]);
      int day = Integer.parseInt(sections[2]);
      return new GregorianCalendar(year, month, day);
    }
    return null;
  }
}
