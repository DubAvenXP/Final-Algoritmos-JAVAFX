package UI.controllers;

import database.models.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Client implements Initializable {
    @FXML
    private TableView<Cliente> tableClients;

    @FXML
    private TableColumn<Cliente, Integer> id;

    @FXML
    private TableColumn<Cliente, String> name;

    @FXML
    private TableColumn<Cliente, String> lastName;

    @FXML
    private TableColumn<Cliente, String> nit;

    @FXML
    private TableColumn<Cliente, String> direction;

    @FXML
    private TableColumn<Cliente, String> phone;

    @FXML
    private TextField idInput;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField lastNameInput;

    @FXML
    private TextField nitInput;

    @FXML
    private TextArea directionInput;

    @FXML
    private TextField phoneInput;

    List<Cliente> clienteList = database.service.ClienteService.listClient();
    ObservableList<Cliente> observableClientList;

    public void addClientsToObservableList(){
        for (int i = 0; i < clienteList.size()-1; i++) {
            FXCollections.observableArrayList(clienteList.get(i));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addClientsToObservableList();
        this.id.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("idCliente"));
        this.nit.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nit"));
        this.name.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
        this.lastName.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellido"));
        this.direction.setCellValueFactory(new PropertyValueFactory<Cliente, String>("direccion"));
        this.phone.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefono"));

        tableClients.setItems(observableClientList);
    }

}
