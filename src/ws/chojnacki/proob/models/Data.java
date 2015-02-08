package ws.chojnacki.proob.models;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Data {
  private List<ProtoContact> contacts;

  public Data() {
    this.contacts = new LinkedList<>();
  }

  public List<ProtoContact> getContacts() {
    return contacts;
  }

  public void setContacts(List<ProtoContact> contacts) {
    this.contacts = contacts;
  }
}
