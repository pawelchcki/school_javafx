package ws.chojnacki.proob;

import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import ws.chojnacki.proob.md.Contact;
import ws.chojnacki.proob.md.Group;

public class MainScreenController implements Initializable {
  @FXML
  private GridPane gridPane;
  @FXML
  private TableView<Contact> tableView;

  private ObservableList<Contact> contacts;

  public TableColumn<Contact, String> addStringColumn(String label, String propertyName, SetStuff x) {
    TableColumn<Contact, String> rv = new TableColumn<>(label);
    rv.setCellFactory(TextFieldTableCell.<Contact>forTableColumn());
    rv.setOnEditCommit(

        (TableColumn.CellEditEvent<Contact, String> event) ->
            x.apply(event.getTableView().getItems()
                        .get(event.getTablePosition().getRow()), event.getNewValue()));

    rv.setCellValueFactory(new PropertyValueFactory<>(propertyName));
    rv.setEditable(true);
    return rv;
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
//    this.tableView = new TableView<>();
//    this.tableView.setEditable(true);

    TableColumn<Contact, Boolean> workContact = new TableColumn<>("Work contact");
    workContact.setCellFactory(tc -> new CheckBoxTableCell<>());
    workContact.setCellValueFactory(new PropertyValueFactory<>("workContact"));

    tableView.getColumns()
        .setAll(addStringColumn("First name", "firstName", Contact::setFirstName),
                addStringColumn("Last name", "lastName", Contact::setLastName),
                addStringColumn("Telephone", "telephone", Contact::setTelephone),
                workContact,
                addStringColumn("Company name", "companyName", Contact::setAddress),
                addStringColumn("Address", "address", Contact::setAddress),
                addStringColumn("City", "city", Contact::setAddress),
                addStringColumn("Zip Code", "zipCode", Contact::setAddress)
        );

    this.contacts = FXCollections.observableArrayList();

    tableView.setItems(contacts);
    tableView.widthProperty().divide(gridPane.widthProperty());
  }
  @FXML
  private void handleLoadAction(ActionEvent event) {
    System.err.println("sad");
  }

  @FXML
  private void handleSaveAction(ActionEvent event) {
    System.err.println("das");
    Group group = new Group();
    group.setName("main");
    group.setContacts(contacts);

    List<Group> groups = new ArrayList<>();
    groups.add(new Group());
    groups.get(0).setName("iik");

    group.setGroups(groups);
    try {
      JAXBContext jc = JAXBContext.newInstance(Group.class, Contact.class);
      Marshaller m = jc.createMarshaller();
      StringWriter w = new StringWriter();
      m.marshal(group, w);
      String xxx = w.toString();
      System.err.println(xxx);
//
    } catch (JAXBException e) {
      throw new RuntimeException(e);
    }
  }

  public void handleAddAction(ActionEvent actionEvent) {
    this.contacts.add(new Contact());
  }

  private interface SetStuff {
    public void apply(Contact contact, String value);
  }
}
