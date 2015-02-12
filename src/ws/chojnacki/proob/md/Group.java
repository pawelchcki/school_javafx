package ws.chojnacki.proob.md;

import java.util.List;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Entity
@XmlRootElement
public class Group {
  private StringProperty name = new SimpleStringProperty();
  private StringProperty description = new SimpleStringProperty();

  private ObservableList<Contact> contacts = FXCollections.observableArrayList();
  private ObservableList<Group> groups = FXCollections.observableArrayList();

  public Group() {
  }

  @XmlAttribute
  public String getName() {
    return name.get();
  }

  public StringProperty nameProperty() {
    return name;
  }

  public void setName(String name) {
    this.name.set(name);
  }

  @XmlElement(name="contact")
  public ObservableList<Contact> getContacts() {
    return contacts;
  }

  public void setContacts(ObservableList<Contact> contacts) {
    this.contacts = contacts;
  }

  @XmlElement(name="group")
  public ObservableList<Group> getGroups() {
    return groups;
  }

  public void setGroups(ObservableList<Group> groups) {
    this.groups = groups;
  }

  public String getDescription() {
    return description.get();
  }

  public StringProperty descriptionProperty() {
    return description;
  }

  public void setDescription(String description) {
    this.description.set(description);
  }
}