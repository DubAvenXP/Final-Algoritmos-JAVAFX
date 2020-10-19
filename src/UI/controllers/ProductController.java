package UI.controllers;

import database.dao.ProductoDao;
import database.models.Producto;
import database.service.ProductoService;
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

public class ProductController implements Initializable {
    @FXML
    private TableView<Producto> productTable;

    @FXML
    private TableColumn<Producto, Integer> id;

    @FXML
    private TableColumn<Producto, String> name;

    @FXML
    private TableColumn<Producto, Double> price;

    @FXML
    private TableColumn<Producto, Integer> stock;

    @FXML
    private TableColumn<Producto, String> description;

    @FXML
    private TableColumn<Producto, String> provider;

    @FXML
    private TextField idInput;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField priceInput;

    @FXML
    private TextField stockInput;

    @FXML
    private TextArea descriptionInput;

    @FXML
    private TextField providerInput;

    @FXML
    private Button newButton;

    @FXML
    private Button createButton;

    List<Producto> productoList = database.service.ProductoService.listProduct();
    ObservableList<Producto> productoObservableList = FXCollections.observableArrayList();

    public void addProductsToObservableList(){
        productoObservableList.addAll(productoList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idInput.setDisable(true);
        newButton.setDisable(true);
        createButton.setDisable(false);
        addProductsToObservableList();
        id.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("idProducto"));
        name.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));
        price.setCellValueFactory(new PropertyValueFactory<Producto, Double>("precio"));
        description.setCellValueFactory(new PropertyValueFactory<Producto, String>("descripcion"));
        stock.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("stock"));
        provider.setCellValueFactory(new PropertyValueFactory<Producto, String>("Proveedores"));
        productTable.setItems(productoObservableList);
    }

    public void rowSelected(MouseEvent mouseEvent){
        newButton.setDisable(false);
        createButton.setDisable(true);
        Producto producto = productTable.getSelectionModel().getSelectedItem();
        if (producto != null){
            idInput.setText(Integer.toString(producto.getIdProducto()));
            nameInput.setText(producto.getNombre());
            priceInput.setText(Double.toString(producto.getPrecio()));
            descriptionInput.setText(producto.getDescripcion());
            stockInput.setText(Integer.toString(producto.getStock()));
            providerInput.setText(producto.getProvider());
        }
    }

    public Producto getProductInfo(){
        Producto producto = new Producto();
        try{
            producto.setIdProducto(0);
            producto.setNombre(nameInput.getText());
            producto.setPrecio(Double.parseDouble(priceInput.getText()));
            producto.setDescripcion(descriptionInput.getText());
            producto.setStock(Integer.parseInt(stockInput.getText()));
            producto.setProvider(providerInput.getText());
        }catch (NumberFormatException error){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(error + " informacion del producto incorrecta o vacia");
            alert.showAndWait();
            producto = null;
        }
        return producto;
    }

    public void createProduct(MouseEvent mouseEvent){
        Producto producto = getProductInfo();
        if(producto != null){
            database.service.ProductoService.createProduct(producto);
            producto.setIdProducto(ProductoDao.idProducto);
            productoObservableList.add(producto);
            productTable.setItems(productoObservableList);
            newProduct();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Informacion");
            alert.setContentText("Producto creado correctamente");
            alert.showAndWait();
        }
    }

    public void updateProduct(MouseEvent mouseEvent){
        Producto producto = productTable.getSelectionModel().getSelectedItem();
        if (producto == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Ningun producto seleccionado");
            alert.showAndWait();
        } else {
            Producto productoAux = getProductInfo();
            productoAux.setIdProducto(Integer.parseInt(idInput.getText()));
            if(!productoObservableList.contains(productoAux)){
                producto.setIdProducto(productoAux.getIdProducto());
                producto.setNombre(productoAux.getNombre());
                producto.setPrecio(productoAux.getPrecio());
                producto.setDescripcion(productoAux.getDescripcion());
                producto.setStock(productoAux.getStock());
                producto.setProvider(productoAux.getProvider());

                ProductoService.updateProduct(producto);
                productTable.refresh();
                newProduct();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Informacion");
                alert.setContentText("Producto modificado correctamente" +
                        "\nId modificado: " + producto.getIdProducto());
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("El producto seleccionado no ha sido modificada");
                alert.showAndWait();
            }
        }
    }

    public void deleteProduct(MouseEvent mouseEvent){
        Producto producto = productTable.getSelectionModel().getSelectedItem();
        if (producto == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Ningun producto seleccionado");
            alert.showAndWait();
        } else {
            database.service.ProductoService.deleteProduct(producto.getIdProducto());
            productoObservableList.remove(producto);
            productTable.refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Informacion");
            alert.setContentText("Producto eliminado correctamente");
            alert.showAndWait();
        }
    }

    public void newProduct() {
        createButton.setDisable(false);
        newButton.setDisable(true);
        idInput.setText("");
        nameInput.setText("");
        priceInput.setText("");
        descriptionInput.setText("");
        stockInput.setText("");
        providerInput.setText("");
    }

}
