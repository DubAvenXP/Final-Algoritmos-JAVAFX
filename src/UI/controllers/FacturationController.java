package UI.controllers;

import UI.models.ProductoFactura;
import database.models.Producto;
import database.models.Venta;
import database.models.VentaProducto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FacturationController implements Initializable {
    @FXML
    private TextField payMethod;

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

    Double totalToPay;


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

    public void addProductToInvoice(MouseEvent mouseEvent) {
        ProductoFactura productoFactura = generateProductoFactura();
        int cantidad = Integer.parseInt(quantityInput.getText());
        Integer stock = Integer.parseInt(stockInput.getText()) - cantidad;
        database.service.VentaService.updateStock(stock, productoFactura.getId());
        listadoProductosFactura.add(productoFactura);
        facturationTable.refresh();
        emptyFields();
        calculateTotalToPay();
    }


    public void deleteProduct(MouseEvent mouseEvent) {

    }

    public void cancelInvoice(MouseEvent mouseEvent) {

    }

    public void generateInvoice(MouseEvent mouseEvent) {
        List<VentaProducto> ventaProductos = generateVentaProductoObjects();
        for (VentaProducto ventaProducto : ventaProductos) {
            database.service.VentaProductoService.saveBill(ventaProducto);
        }


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

    public void emptyFields() {
        idProductInput.setText("");
        nameProductInput.setText("");
        quantityInput.setText("");
        priceInput.setText("");
        stockInput.setText("");
    }

    public void calculateTotalToPay() {
        totalToPay = 0.0;
        for (ProductoFactura productoFactura : listadoProductosFactura) {
            totalToPay += productoFactura.getPrecioTotal();
        }
        totalToPayInput.setText("Q" + Double.toString(totalToPay));
    }

    public List<VentaProducto> generateVentaProductoObjects() {
        List<VentaProducto> ventaProductos = new ArrayList<>();
        for (ProductoFactura productoFactura : listadoProductosFactura) {
            VentaProducto ventaProducto = new VentaProducto();
            ventaProducto.setIdProducto(0);
            ventaProducto.setSerieVenta("0");
            ventaProducto.setIdProducto(productoFactura.getId());
            ventaProducto.setCantidad(productoFactura.getCantidad());
            ventaProducto.setPrecioVenta(productoFactura.getPrecioTotal());
        }
        return ventaProductos;
    }

    public Venta generateVenta() {
        Venta venta = new Venta();
        venta.setIdVenta(0);
        return venta;
    }
}
