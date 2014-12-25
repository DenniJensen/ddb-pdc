package de.ddb.pdc.metadata;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RDF",
    namespace = "http://www.w3.org/1999/02/22-rdf-syntax-ns#")
public class DNBLocationItem {

  @XmlElement(name = "Description",
      namespace = "http://www.w3.org/1999/02/22-rdf-syntax-ns#")
  private Description description;

  /**
   * @return locate of a dnb location item
   */
  public String locate() {
    if (this.description == null) {
      return null;
    }
    return this.description.locate();
  }

  private static class Description {

    @XmlElement(name = "geographicAreaCode",
        namespace = "http://d-nb.info/standards/elementset/gnd#")
    private Geo geo;

    public String locate() {
      if (this.geo == null) {
        return null;
      }
      return this.geo.getLocate();
    }

    private static class Geo {

      @XmlAttribute(namespace = "http://www.w3.org/1999/02/22-rdf-syntax-ns#")
      private String resource;

      public String getLocate() {
        if (resource != null) {
          String[] temp = resource.split("#");
          String[] temp2 = temp[temp.length - 1].split("-");
          if (temp2.length > 2) {
            return temp2[1].toLowerCase();
          }
        }
        return null;
      }
    }
  }
}
