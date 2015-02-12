package ws.chojnacki.proob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
  @FXML
  private TextField textField;


  private FilteredList<Contact> filteredData;
  private ObservableList<Group> groups;

  private Group mainGroup;
  private Group currentGroup;

  /**
   * Method responsible for adding columns to table view
   *
   * @param label        Button Label
   * @param propertyName Name of the property bind To
   * @param x            JAVA 8 magic enabling setting of the value when new data is set
   * @param <T>          Type of either Contact or Group
   */
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
                addStringColumn("Company name", "companyName", Contact::setCompanyName),
                addStringColumn("Address", "address", Contact::setAddress),
                addStringColumn("City", "city", Contact::setCity),
                addStringColumn("Zip Code", "zipCode", Contact::setZipCode)
        );

    tableView.widthProperty().divide(gridPane.widthProperty());
  }

  public void updateCurrentGroup(Group group) {
    currentGroup = group;
    filteredData = new FilteredList<>(group.getContacts(), p -> true);
    tableView.setItems(filteredData);
    groupTableView.setItems(group.getGroups());
  }

  public Pattern textToRegexp(String str) {
    return Pattern.compile(
        "^"
            .concat(
                str
                    .replaceAll("\\*", ".*?")
                    .replaceAll("\\?", ".")
            )
            .concat("$"), Pattern.CASE_INSENSITIVE);
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
        if (event.getClickCount() == 2 && (!row.isEmpty())) {
          updateCurrentGroup(row.getItem());
        }
      });
      return row;
    });

    textField.textProperty().addListener((observable, oldValue, newValue) -> {
      filteredData.setPredicate((contact) -> {
        if (newValue == null || newValue.isEmpty()) {
          return true;
        }

        List<String> list = new LinkedList<>();
        list.add(contact.getAddress());
        list.add(contact.getFirstName());
        list.add(contact.getLastName());
        list.add(contact.getZipCode());
        list.add(contact.getTelephone());
        list.add(contact.getCity());

        if (contact.getWorkContact()) {
          list.add(contact.getCompanyName());
        }
        Stream<String> stream = list.stream().filter(x -> x != null).filter(
            textToRegexp(newValue).asPredicate()
        );

        return stream.count() > 0;
      });
    });

    if (Files.exists(Paths.get(getConfigurationFilePath()))) {
      handleLoadAction(null);
    } else {
      mainGroup = new Group();
    }
    updateCurrentGroup(mainGroup);
  }

  private String getConfigurationFilePath() {
    String path = System.getProperty("user.home") + File.separator + ".leftoverfile_70950";
    return path;
  }

  @FXML
  private void handleLoadAction(ActionEvent event) {
    System.err.println("Load");
    try {
      JAXBContext jc = JAXBContext.newInstance(Group.class, Contact.class);
      Unmarshaller m = jc.createUnmarshaller();
      InputStream is = new FileInputStream(getConfigurationFilePath());
      this.mainGroup = (Group) m.unmarshal(is);
      updateCurrentGroup(this.mainGroup);
      is.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @FXML
  private void handleSaveAction(ActionEvent event) {
    System.err.println("Save");
    try {
      JAXBContext jc = JAXBContext.newInstance(Group.class, Contact.class);
      Marshaller m = jc.createMarshaller();
      OutputStream os = new FileOutputStream(getConfigurationFilePath());
      m.marshal(this.mainGroup, os);
      os.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void handleAddAction(ActionEvent actionEvent) {
    currentGroup.getContacts().add(new Contact());
    updateCurrentGroup(currentGroup);
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
