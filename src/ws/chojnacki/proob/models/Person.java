package ws.chojnacki.proob.models;

import javax.persistence.Entity;

import javafx.beans.property.StringProperty;

@Entity
public class Person extends ProtoContact {

  private StringProperty firstName;
  private StringProperty lastName;
  private Address address;

  @Override
  public String getId() {
    return String.format("%s_%s", firstName.get().toLowerCase(), lastName.get().toLowerCase());
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public String getFirstName() {
    return firstName.get();
  }

  public void setFirstName(String firstName) {
    this.firstName.set(firstName);
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
