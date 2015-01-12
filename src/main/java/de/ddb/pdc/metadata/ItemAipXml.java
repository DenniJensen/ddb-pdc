package de.ddb.pdc.metadata;

import org.springframework.xml.xpath.XPathOperations;
import org.w3c.dom.Node;

import javax.xml.transform.dom.DOMSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to operate with xpath the DDB XML AIP request.
 */
public class ItemAipXml {

  private DOMSource domSource;
  private XPathOperations xpath;

  /**
   * Create new ItemAipXml Object for every DomSource
   * The class is need to operate with xpath on the dom
   *
   * @param domSource xml source in a dom
   * @param xpath use for xpath operations
   */
  public ItemAipXml(DOMSource domSource, XPathOperations xpath) {
    this.domSource = domSource;
    this.xpath = xpath;
  }

  /**
   * Returns the title of the item.
   */
  public String getTitle() {
    return xpath.evaluateAsString("//ctx:preview/ctx:title", domSource);
  }

  /**
   * Returns the subtitle of the item.
   */
  public String getSubtitle() {
    return xpath.evaluateAsString("//ctx:preview/ctx:subtitle", domSource);
  }

  /**
   * Returns url to thumbnail of the item.
   */
  public String getThumbnail() {
    return xpath.evaluateAsString("//ctx:preview/ctx:thumbnail/@href", domSource);
  }

  /**
   * Returns the published year.
   */
  public int getPublishedYear() {
    String date = xpath.evaluateAsString("//dcterms:issued", domSource);
    try {
      return Integer.parseInt(MetadataUtils.useRegex(date,"\\d{4}" ));
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  /**
   * Returns name of the institution.
   */
  public String getInstitution() {
    return xpath.evaluateAsString("//edm:dataProvider[1]", domSource);
  }

  /**
   * Returns a list with the authors dnb urls.
   */
  public List<String> getAuthorUrls() {
    List<String> authorUrls = new ArrayList<String>();
    String xpathFacetRole =
        "//ctx:facet[@name='affiliate_fct_role_normdata']/ctx:value";
    String xpathFacet =
        "//ctx:facet[@name='affiliate_fct_normdata']/ctx:value";
    List<Node> nodes = xpath.evaluateAsNodeList(xpathFacetRole, domSource);
    for (Node node : nodes) {
      String value = node.getFirstChild().getTextContent();
      if (value.endsWith("_1_affiliate_fct_subject")
          || value.endsWith("_1_affiliate_fct_involved")) {
        authorUrls.add(value.split("_")[0]);
      }
    }
    if (authorUrls.size() == 0) {
      nodes = xpath.evaluateAsNodeList(xpathFacet, domSource);
      for (Node node : nodes) {
        authorUrls.add(node.getFirstChild().getTextContent());
      }
    }
    return  authorUrls;
  }


}
