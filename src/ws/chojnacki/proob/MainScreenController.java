package ws.chojnacki.proob;

import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
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
  @FXML
  private TableView<Group> groupTableView;

  private ObservableList<Contact> contacts;
  private ObservableList<Group> groups;

  private Group mainGroup;
  private Group currentGroup;

  public static <T> TableColumn<T, String> addStringColumn(String label, String propertyName,
                                                      SetStuff<T> x) {
    TableColumn<T, String> rv = new TableColumn<>(label);
    rv.setCellFactory(TextFieldTableCell.<T>forTableColumn());
    rv.setOnEditCommit(
        (TableColumn.CellEditEvent<T, String> event) ->
            x.apply(event.getTableView().getItems()
                        .get(event.getTablePosition().getRow()), event.getNewValue()));

    rv.setCellValueFactory(new PropertyValueFactory<>(propertyName));
    rv.setEditable(true);
    return rv;
  }

  public void initContacts() {
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

    tableView.widthProperty().divide(gridPane.widthProperty());
  }

  public void updateCurrentGroup(Group group) {
    currentGroup = group;
    tableView.setItems(group.getContacts());
    groupTableView.setItems(group.getGroups());
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    initContacts();
    groupTableView.getColumns().addAll(
        addStringColumn("Name", "name", Group::setName),
        addStringColumn("Description", "decsription", Group::setDescription)
    );

    groupTableView.setRowFactory(rv -> {
      TableRow<Group> row = new TableRow<>();
      row.setOnMouseClicked(event -> {
        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
          updateCurrentGroup(row.getItem());
        }
      });
      return row;
    });

    mainGroup = new Group();
    updateCurrentGroup(mainGroup);
//    this.tableView = new TableView<>();
//    this.tableView.setEditable(true);

  }

  @FXML
  private void handleLoadAction(ActionEvent event) {
    System.err.println("sad");
  }

  @FXML
  private void handleSaveAction(ActionEvent event) {
    System.err.println("das");
    try {
      JAXBContext jc = JAXBContext.newInstance(Group.class, Contact.class);
      Marshaller m = jc.createMarshaller();
      StringWriter w = new StringWriter();
      m.marshal(this.mainGroup, w);

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

  public void handleAddGroupAction(ActionEvent actionEvent) {
    currentGroup.getGroups().add(new Group());
  }

  public void handleRootGroup(ActionEvent actionEvent) {
    updateCurrentGroup(mainGroup);
  }

  private interface SetStuff<T> {

    public void apply(T contact, String value);
  }
}
