package de.ddb.pdc.metadata;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Calendar;
import java.util.GregorianCalendar;

@XmlRootElement(name = "RDF", namespace =
    "http://www.w3.org/1999/02/22-rdf-syntax-ns#")
public class DNBAuthorItem {

  @XmlElement(name = "Description", namespace = "http://www.w3.org/1999/02/22-rdf-syntax-ns#")
  private RDFDescription description;

  /**
   * @return name of the author
   */
  public String name() {
    if (this.description == null) {
      return null;
    }
    return this.description.getName();
  }

  /**
   * @return date of birth of the author
   */
  public Calendar dateOfBirth() {
    if (this.description == null) {
      return null;
    }
    return formatDateString(this.description.getDateOfBirth());
  }

  /**
   * @return date of death of the author
   */
  public Calendar dateOfDeath() {
    if (this.description == null) {
      return null;
    }
    return formatDateString(this.description.getDateOfDeath());
  }

  /**
   * @return the uri of the place of death from author
   */
  public String placeOfDeathUri() {
    if (this.description == null) {
      return null;
    }
    return this.description.placeOfDeathUri();
  }

  private static class RDFDescription {

    @XmlElement(name = "variantnameForThePersion",
        namespace = "http://d-nb.info/standards/elementset/gnd#")
    private String name;

    @XmlElement(namespace = "http://d-nb.info/standards/elementset/gnd#")
    private String dateOfBirth;

    @XmlElement(namespace = "http://d-nb.info/standards/elementset/gnd#")
    private String dateOfDeath;

    @XmlElement(namespace = "http://d-nb.info/standards/elementset/gnd#")
    private DeathPlace placeOfDeath;

    public String getName() {
      return this.name;
    }

    public String getDateOfBirth() {
      return this.dateOfBirth;
    }

    public String getDateOfDeath() {
      return this.dateOfDeath;
    }

    public String placeOfDeathUri() {
      if (this.placeOfDeath == null) {
        return null;
      }
      return this.placeOfDeath.deathPlaceUri();
    }

    private static class DeathPlace {

      @XmlElement(name = "Description",
          namespace = "http://www.w3.org/1999/02/22-rdf-syntax-ns#")
      private DPDescription description;

      public String deathPlaceUri() {
        if (this.description == null) {
          return null;
        }
        return this.description.getAbout();
      }

      private static class DPDescription {

        @XmlAttribute(
            namespace = "http://www.w3.org/1999/02/22-rdf-syntax-ns#")
        private String about;

        public String getAbout() {
          return this.about;
        }
      }
    }
  }

  private Calendar formatDateString(String date) {
    Calendar cal = null;
    String[] temp = date.split("-");
    if (temp.length == 3) {
      cal = new GregorianCalendar();
      cal.set(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),
          Integer.parseInt(temp[2]));
    }
    return cal;
  }
}
