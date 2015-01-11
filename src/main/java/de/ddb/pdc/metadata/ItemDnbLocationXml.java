package de.ddb.pdc.metadata;

import org.springframework.xml.xpath.XPathOperations;

import javax.xml.transform.dom.DOMSource;

/**
 * Class to operate with xpath on DNB location request xml.
 */
public class ItemDnbLocationXml {

  private DOMSource domSource;
  private XPathOperations xpath;

  /**
   * Create a new ItemDnbLocationXml Object for every Dnb location request
   * Need to operate with xpath on the dom.
   *
   * @param domSource source on the xml dom
   * @param xpath use for xpath operations
   */
  public ItemDnbLocationXml(DOMSource domSource, XPathOperations xpath) {
    this.domSource = domSource;
    this.xpath = xpath;
  }

  /**
   * Returns the iso 2 country code from the resource attribute.
   */
  public String getIso2CountryCode() {
    String location = xpath.evaluateAsString(
        "//gndo:geographicAreaCode/@rdf:resource", domSource);
    return iso2Locate(location);
  }

  private String iso2Locate(String location) {
    if (location != null) {
      String[] temp = location.split("#");
      String[] temp2 = temp[temp.length - 1].split("-");
      if (temp2.length >= 2) {
        int length = temp2[1].length();
        return temp2[1].substring(length - 2, length).toLowerCase();
      }
    }
    return null;
  }
}
