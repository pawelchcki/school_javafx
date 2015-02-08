package ws.chojnacki.proob;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import ws.chojnacki.proob.md.Contact;

public class MainScreenController implements Initializable {

  @FXML
  private GridPane gridPane;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    TableView<Contact> tableView = new TableView<>();
    TableColumn<Contact, String> firstName = new TableColumn<>("First name");
    firstName.setCellFactory(TextFieldTableCell.<Contact>forTableColumn());
    firstName.setOnEditCommit(
        (TableColumn.CellEditEvent<Contact, String> event) -> event.getTableView().getItems()
            .get(event.getTablePosition().getRow())
            .setFirstName(
                event.getNewValue()));

    firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    firstName.setEditable(true);
    tableView.setEditable(true);

    tableView.getColumns().setAll(firstName);
    ObservableList<Contact> contacts = FXCollections.observableArrayList();

    contacts.addAll(new Contact(), new Contact());
    tableView.setItems(contacts);

    gridPane.add(tableView, 1, 1);
  }
}
