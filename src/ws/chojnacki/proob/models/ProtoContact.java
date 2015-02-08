package ws.chojnacki.proob.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;

@Entity
abstract class ProtoContact {
  @Id
  @XmlID
  @XmlAttribute
  abstract String getId();
}
