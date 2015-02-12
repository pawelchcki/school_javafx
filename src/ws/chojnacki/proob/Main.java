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

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    primaryStage.setTitle("Contact app");
    root.minWidth(1300);
    root.maxWidth(1300);
    root.minHeight(700);
    root.minWidth(700);
    Scene scene = new Scene(root, 1300, 700);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) throws JAXBException {
    launch(args);
  }
}
