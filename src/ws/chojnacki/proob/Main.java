package ws.chojnacki.proob;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ws.chojnacki.proob.models.Data;
import ws.chojnacki.proob.models.Group;
import ws.chojnacki.proob.models.Person;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    primaryStage.setTitle("Hello World");
    primaryStage.setScene(new Scene(root, 300, 275));
    primaryStage.show();
  }



  public static void main(String[] args) throws JAXBException {
//    Person p = new Person("Stefan", "Batory");
//    Data c = new Data();
//    Group g = new Group("name1");
//    Group g2 = new Group("name2");
//    c.getContacts().add(g2);
//
//    c.getContacts().add(g);
//    c.getContacts().add(p);
//    JAXBContext jc = JAXBContext.newInstance(Data.class, Group.class, Person.class);
//    Marshaller m = jc.createMarshaller();
//    StringWriter w = new StringWriter();
//    m.marshal(c, w);
//    String xxx = w.toString();
//
//    StringReader stringReader = new StringReader(xxx);
//    Unmarshaller unmarchaller = jc.createUnmarshaller();
//    Data data2 = (Data) unmarchaller.unmarshal(stringReader);
//    System.err.println(data2.getContacts());
    launch(args);
  }
}
