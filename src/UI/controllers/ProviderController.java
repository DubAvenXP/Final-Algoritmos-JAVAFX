package UI.controllers;

import database.dao.ProveedorDao;
import database.models.Proveedor;
import database.service.ProveedorService;
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

    public void rowSelected(MouseEvent mouseEvent) {
        newButton.setDisable(false);
        createButton.setDisable(true);
        Proveedor proveedor = providersTable.getSelectionModel().getSelectedItem();
        if (proveedor != null) {
            idInput.setText(Integer.toString(proveedor.getIdProveedor()));
            nameInput.setText(proveedor.getNombre());
            descriptionInput.setText(proveedor.getDescripcion());
        }
    }

    public Proveedor getProviderInfo(){
        Proveedor proveedor = new Proveedor();
        try{
            proveedor.setIdProveedor(0);
            proveedor.setNombre(nameInput.getText());
            proveedor.setDescripcion(descriptionInput.getText());
        }catch (NumberFormatException error){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(error + " informacion del proveedor incorrecta o vacia");
            alert.showAndWait();
            proveedor = null;
        }
        return proveedor;
    }

    public void createProvider(MouseEvent mouseEvent) {
        Proveedor proveedor = getProviderInfo();
        if (proveedor != null) {
            database.service.ProveedorService.createProvider(proveedor);
            proveedor.setIdProveedor(ProveedorDao.idProveedor);
            proveedorObservableList.add(proveedor);
            providersTable.setItems(proveedorObservableList);
            newProvider();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Informacion");
            alert.setContentText("Proveedor creado correctamente");
            alert.showAndWait();
        }
    }

    public void updateProvider(MouseEvent mouseEvent) {
        Proveedor proveedor = providersTable.getSelectionModel().getSelectedItem();
        if (proveedor == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Ningun cliente seleccionado");
            alert.showAndWait();
        } else {
            Proveedor proveedorAux = getProviderInfo();
            proveedorAux.setIdProveedor(Integer.parseInt(idInput.getText()));
            if (!proveedorObservableList.contains(proveedorAux)) {
                proveedor.setIdProveedor(proveedorAux.getIdProveedor());
                proveedor.setNombre(proveedorAux.getNombre());
                proveedor.setDescripcion(proveedorAux.getDescripcion());

                ProveedorService.updateProvider(proveedor);
                providersTable.refresh();
                newProvider();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Informacion");
                alert.setContentText("Proveedor modificado correctamente" +
                        "\nId modificado: " + proveedor.getIdProveedor());
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("El proveedor seleccionado no ha sido modificada");
                alert.showAndWait();
            }
        }
    }

    public void deleteProvider(MouseEvent mouseEvent) {
        Proveedor proveedor = providersTable.getSelectionModel().getSelectedItem();
        if (proveedor == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Ningun proveedor seleccionado");
            alert.showAndWait();
        } else {
            database.service.ProveedorService.deleteProvider(proveedor.getIdProveedor());
            proveedorObservableList.remove(proveedor);
            providersTable.refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Informacion");
            alert.setContentText("Proveedor eliminado correctamente");
            alert.showAndWait();
        }
    }

    public void newProvider() {
        createButton.setDisable(false);
        newButton.setDisable(true);
        idInput.setText("");
        nameInput.setText("");
        descriptionInput.setText("");
    }
}
