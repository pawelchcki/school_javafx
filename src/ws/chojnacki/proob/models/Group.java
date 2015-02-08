package ws.chojnacki.proob.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class Group extends ProtoContact implements Serializable {

  private String name;
  private List<ProtoContact> contacts;

  public Group() {
  }

  public Group(String name) {
    this.name = name;
  }

  @Override
  String getId() {
    return this.name;
  }

  public List<ProtoContact> getContacts() {
    return contacts;
  }

  public void setContacts(List<ProtoContact> contacts) {
    this.contacts = contacts;
  }
}