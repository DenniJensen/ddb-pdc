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

  /**
   * Create ItemDnbAuthorXml Object for dnb author request.
   * Need to operate with xpath on the dom.
   *
   * @param domSource source of the dom
   * @param xpath use for xpath operations
   */
  public ItemDnbAuthorXml(DOMSource domSource, XPathOperations xpath) {
    this.domSource = domSource;
    this.xpath = xpath;
  }

  /**
   * Returns name of the author.
   */
  public String getName() {
    String name = xpath.evaluateAsString(
        "//gndo:variantNameForThePerson", domSource);
    if (name.equals("")) {
      return null;
    }
    return name;
  }

  /**
   * Returns a calendar object with date of birth.
   */
  public Calendar getDateOfBirth() {
    String dateOfBirth = xpath.evaluateAsString(
        "//gndo:dateOfBirth", domSource);
    return formatStringToDate(dateOfBirth);
  }

  /**
   * Returns a calendar object with date of death.
   */
  public Calendar getDateOfDeath() {
    String dateOfDeath = xpath.evaluateAsString(
        "//gndo:dateOfDeath", domSource);
    return formatStringToDate(dateOfDeath);
  }

  /**
   * Returns DNB URL to place of birth.
   */
  public String getPlaceOfBirth() {
    String pob = xpath.evaluateAsString(
        "//gndo:placeOfBirth/rdf:Description/@rdf:about", domSource);
    if (pob.equals("")) {
      return null;
    }
    return pob;
  }

  /**
   * Returns DNB URL to place of death.
   */
  public String getPlaceOfDeath() {
    String pod = xpath.evaluateAsString(
        "//gndo:placeOfDeath/rdf:Description/@rdf:about", domSource);
    if (pod.equals("")) {
      return null;
    }
    return pod;
  }

  private Calendar formatStringToDate(String date) {
    String[] sections = date.split("-");
    if (sections.length > 0) {
      try {
        int year = Integer.parseInt(sections[0]);
        int month = 0;
        int day = 1;
        if (sections.length > 2) {
          month = Integer.parseInt(sections[1]);
          if (month > 0) {
            month--;
          }
          day = Integer.parseInt(sections[2]);
        }
        return new GregorianCalendar(year, month, day);
      } catch (NumberFormatException e) {
        return null;
      }
    }
    return null;
  }
}
