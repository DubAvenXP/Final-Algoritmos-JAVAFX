package UI.controllers;

import database.models.Proveedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProviderController implements Initializable {
    @FXML
    private TableView<Proveedor> providersTable;

    @FXML
    private TableColumn<Proveedor, Integer> id;

    @FXML
    private TableColumn<Proveedor, String> name;

    @FXML
    private TableColumn<Proveedor, String> description;

    @FXML
    private TextField idInput;

    @FXML
    private TextField nameInput;

    @FXML
    private TextArea descriptionInput;

    @FXML
    private Button newButton;

    @FXML
    private Button createButton;

    List<Proveedor> proveedorList = database.service.ProveedorService.listProvider();
    ObservableList<Proveedor> proveedorObservableList = FXCollections.observableArrayList();

    public void addProvidersToObservableList(){
        proveedorObservableList.addAll(proveedorList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idInput.setDisable(true);
        newButton.setDisable(true);
        createButton.setDisable(false);
        addProvidersToObservableList();

        id.setCellValueFactory(new PropertyValueFactory<Proveedor, Integer>("idProveedor"));
        name.setCellValueFactory(new PropertyValueFactory<Proveedor, String>("nombre"));
        description.setCellValueFactory(new PropertyValueFactory<Proveedor, String>("descripcion"));

        providersTable.setItems(proveedorObservableList);
    }

}
