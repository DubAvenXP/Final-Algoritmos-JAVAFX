package UI.controllers;

import database.models.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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

    @FXML
    private Button newButton;

//    ObservableList<Cliente> observableClientList = FXCollections.observableArrayList(
//            new Cliente(1, "1254895-5", "pepe", "perez", "ciudad", "2154-8540"),
//            new Cliente(2, "1254895-0", "pedro", "pelaez", "ciudad", "2154-8247"),
//            new Cliente(3, "1254895-2", "juan", "najera", "ciudad", "2154-1547"),
//            new Cliente(4, "1254895-3", "alex", "garcia", "ciudad", "2154-8537"),
//            new Cliente(5, "1254895-6", "rita", "rockefeller", "ciudad", "2154-8547")
//    );

    List<Cliente> clienteList = database.service.ClienteService.listClient();
    ObservableList<Cliente> observableClientList = FXCollections.observableArrayList();


    public void addClientsToObservableList(){
        for (int i = 0; i < clienteList.size(); i++) {
//            FXCollections.observableArrayList(clienteList.get(i));
            observableClientList.add(clienteList.get(i));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idInput.setDisable(true);
        addClientsToObservableList();
        this.id.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("idCliente"));
        this.nit.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nit"));
        this.name.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
        this.lastName.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellido"));
        this.direction.setCellValueFactory(new PropertyValueFactory<Cliente, String>("direccion"));
        this.phone.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefono"));

        tableClients.setItems(observableClientList);
        newButton.setDisable(false);
    }


    public void rowSelected(MouseEvent mouseEvent) {
        newButton.setDisable(false);
        Cliente cliente = tableClients.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            idInput.setText(Integer.toString(cliente.getIdCliente()));
            nameInput.setText(cliente.getNombre());
            lastNameInput.setText(cliente.getApellido());
            nitInput.setText(cliente.getNit());
            directionInput.setText(cliente.getDireccion());
            phoneInput.setText(cliente.getTelefono());
        }
    }

    public Cliente getClientInfo() {
        Cliente cliente = new Cliente();
        try {
            cliente.getIdCliente();
            cliente.setNit(nitInput.getText());
            cliente.setNombre(nameInput.getText());
            cliente.setApellido(lastNameInput.getText());
            cliente.setDireccion(directionInput.getText());
            cliente.setTelefono(phoneInput.getText());
        } catch (NumberFormatException error) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(error + " informacion del cliente incorrecta o vacia");
            alert.showAndWait();
            cliente = null;
        }
        return cliente;
    }

    public void createClient(MouseEvent mouseEvent) {
        Cliente cliente = getClientInfo();
        if (cliente != null) {
            database.service.ClienteService.createClient(cliente);
            observableClientList.add(cliente);
            tableClients.setItems(observableClientList);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Informacion");
            alert.setContentText("Cliente creado correctamente");
            alert.showAndWait();
        }
    }

    public void updateClient(MouseEvent mouseEvent) {
        Cliente cliente = tableClients.getSelectionModel().getSelectedItem();
        if (cliente == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Ningun cliente seleccionado");
            alert.showAndWait();
        } else {
            Cliente clienteAux = getClientInfo();
            if (observableClientList.contains(clienteAux)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("La persona seleccionada no ha sido modificada");
                alert.showAndWait();
            } else {
                cliente.setIdCliente(clienteAux.getIdCliente());
                cliente.setNombre(clienteAux.getNombre());
                cliente.setApellido(clienteAux.getApellido());
                cliente.setNit(clienteAux.getNit());
                cliente.setDireccion(clienteAux.getDireccion());
                cliente.setTelefono(clienteAux.getTelefono());

                database.service.ClienteService.updateClient(cliente);
                tableClients.refresh();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Informacion");
                alert.setContentText("Persona modificada correctamente");
                alert.showAndWait();
            }
        }
    }

    public void deleteClient(MouseEvent mouseEvent) {
        Cliente cliente = tableClients.getSelectionModel().getSelectedItem();
        if (cliente == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Ningun cliente seleccionado");
            alert.showAndWait();
        } else {
            database.service.ClienteService.deleteClient(cliente.getNit());
            observableClientList.remove(cliente);
            tableClients.refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Informacion");
            alert.setContentText("Cliente eliminado correctamente");
            alert.showAndWait();
        }
    }

    public void newClient(MouseEvent mouseEvent) {
        idInput.setDisable(false);
        newButton.setDisable(true);
        idInput.setText("");
        nameInput.setText("");
        lastNameInput.setText("");
        nitInput.setText("");
        directionInput.setText("");
        phoneInput.setText("");
    }
}
