package ws.chojnacki.proob.md;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAttribute;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
public class Contact {
  private StringProperty firstName = new SimpleStringProperty();
  private StringProperty lastName = new SimpleStringProperty();
  private StringProperty telephone = new SimpleStringProperty();
  private BooleanProperty workContact = new SimpleBooleanProperty();
  private StringProperty companyName = new SimpleStringProperty();
  private StringProperty address = new SimpleStringProperty();
  private StringProperty city = new SimpleStringProperty();
  private StringProperty zipCode = new SimpleStringProperty();

  @XmlAttribute
  public String getFirstName() {
    return firstName.get();
  }

  public void setFirstName(String firstName) {
    this.firstName.set(firstName);
  }

  public StringProperty firstNameProperty() {
    return firstName;
  }

  @XmlAttribute
  public String getLastName() {
    return lastName.get();
  }

  public void setLastName(String lastName) {
    this.lastName.set(lastName);
  }

  public StringProperty lastNameProperty() {
    return lastName;
  }

  @XmlAttribute
  public String getTelephone() {
    return telephone.get();
  }

  public void setTelephone(String telephone) {
    this.telephone.set(telephone);
  }

  public StringProperty telephoneProperty() {
    return telephone;
  }

  @XmlAttribute
  public boolean getWorkContact() {
    return workContact.get();
  }

  public void setWorkContact(boolean workContact) {
    this.workContact.set(workContact);
  }

  public BooleanProperty workContactProperty() {
    return workContact;
  }

  @XmlAttribute
  public String getCompanyName() {
    return companyName.get();
  }

  public void setCompanyName(String companyName) {
    this.companyName.set(companyName);
  }

  public StringProperty companyNameProperty() {
    return companyName;
  }

  @XmlAttribute
  public String getAddress() {
    return address.get();
  }

  public void setAddress(String address) {
    this.address.set(address);
  }

  public StringProperty addressProperty() {
    return address;
  }

  @XmlAttribute
  public String getCity() {
    return city.get();
  }

  public void setCity(String city) {
    this.city.set(city);
  }

  public StringProperty cityProperty() {
    return city;
  }

  @XmlAttribute
  public String getZipCode() {
    return zipCode.get();
  }

  public void setZipCode(String zipCode) {
    this.zipCode.set(zipCode);
  }

  public StringProperty zipCodeProperty() {
    return zipCode;
  }
}
