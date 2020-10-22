package UI.controllers;

import UI.models.ProductoFactura;
import database.models.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class FacturationController implements Initializable {
    @FXML
    private TextField idFacturationInput;

    @FXML
    private TextField nitClientInput;

    @FXML
    private TextField idProductInput;

    @FXML
    private TextField priceInput;

    @FXML
    private TextField quantityInput;

    @FXML
    private Button addButton;

    @FXML
    private DatePicker dateInput;

    @FXML
    private TextField nameClientInput;

    @FXML
    private TextField nameProductInput;

    @FXML
    private TextField stockInput;

    @FXML
    private TextField sellerId;

    @FXML
    private TableView<ProductoFactura> facturationTable;

    @FXML
    private TableColumn<ProductoFactura, Integer> index;

    @FXML
    private TableColumn<ProductoFactura, Integer> id;

    @FXML
    private TableColumn<ProductoFactura, String> product;

    @FXML
    private TableColumn<ProductoFactura, Integer> quantity;

    @FXML
    private TableColumn<ProductoFactura, Double> price;

    @FXML
    private TableColumn<ProductoFactura, Double> totalPrice;

    @FXML
    private Button deleteButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button generateButton;

    @FXML
    private Button searchClientButton;

    @FXML
    private Button searchProductButton;

    @FXML
    private TextField totalItemsInput;

    @FXML
    private TextField totalToPayInput;


    ObservableList<ProductoFactura> listadoProductosFactura = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.index.setCellValueFactory(new PropertyValueFactory<ProductoFactura, Integer>("index"));
        this.id.setCellValueFactory(new PropertyValueFactory<ProductoFactura, Integer>("id"));
        this.product.setCellValueFactory(new PropertyValueFactory<ProductoFactura, String>("producto"));
        this.quantity.setCellValueFactory(new PropertyValueFactory<ProductoFactura, Integer>("cantidad"));
        this.price.setCellValueFactory(new PropertyValueFactory<ProductoFactura, Double>("precioUnitario"));
        this.totalPrice.setCellValueFactory(new PropertyValueFactory<ProductoFactura, Double>("precioTotal"));
        facturationTable.setItems(listadoProductosFactura);
    }

    public void rowSelected(MouseEvent mouseEvent) {

    }

    public void searchClientOnClic(MouseEvent mouseEvent) {
        String clientNit = nitClientInput.getText();
        String clientName = database.service.ClienteService.listClientNit(clientNit);

        if (clientName.length() > 0) {
            nameClientInput.setText(clientName);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Cliente no encontrado");
            alert.showAndWait();
        }
    }

    public void searchProductOnClic(MouseEvent mouseEvent) {
        Integer productId = Integer.parseInt(idProductInput.getText());
        Producto producto = database.service.ProductoService.listProductByID(productId);
        if (producto.getNombre() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Producto no encontrado");
            alert.showAndWait();
        } else {
            nameProductInput.setText(producto.getNombre());
            priceInput.setText(Double.toString(producto.getPrecio()));
            stockInput.setText(Integer.toString(producto.getStock()));
        }


    }

    /*Douglas el modelo de factura es el que ira en la tabla (mira la tabla de la UI)
    el modelo lleva indice, ID, nombreProducto, cantidad, precio... total
    cada linea es un objeto
     */
    public void addProductToInvoice(MouseEvent mouseEvent) {
        ProductoFactura productoFactura = generateProductoFactura();
        listadoProductosFactura.add(productoFactura);
        facturationTable.refresh();
    }


    public void deleteProduct(MouseEvent mouseEvent) {

    }

    public void cancelInvoice(MouseEvent mouseEvent) {

    }

    public void generateInvoice(MouseEvent mouseEvent) {

    }

    public ProductoFactura generateProductoFactura() {
        ProductoFactura productoFactura = new ProductoFactura();
        Double precioUnitario = Double.parseDouble(priceInput.getText());
        Integer cantidad = Integer.parseInt(quantityInput.getText());
        Double precioTotal = precioUnitario * cantidad;

        productoFactura.setIndex(listadoProductosFactura.size() + 1);
        productoFactura.setId(Integer.parseInt(idProductInput.getText()));
        productoFactura.setProducto(nameProductInput.getText());
        productoFactura.setCantidad(cantidad);
        productoFactura.setPrecioUnitario(precioUnitario);
        productoFactura.setPrecioTotal(precioTotal);

        return productoFactura;
    }

    public void emptyFields(){
        idProductInput.setText("");
        nameProductInput.setText("");
        quantityInput.setText("");
    }
}
