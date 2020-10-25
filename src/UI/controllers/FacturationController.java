package UI.controllers;

import UI.models.ProductoFactura;
import database.dao.VentaDao;
import database.models.Producto;
import database.models.SaldoPendiente;
import database.models.Venta;
import database.models.VentaProducto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FacturationController implements Initializable {
    @FXML
    private ComboBox<String> payMethod;

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
    private TextField sellerUser;

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
    private TextField serialNumber;

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

    //variable estatica que genera el serie venta
    public static String serial = VentaDao.generateBillNumber();
    ObservableList<String> payMethods = FXCollections.observableArrayList(
            "Efectivo", "Tarjeta", "Cuotas"
    );

    ObservableList<ProductoFactura> listadoProductosFactura = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        payMethod.setItems(payMethods);
        this.index.setCellValueFactory(new PropertyValueFactory<ProductoFactura, Integer>("index"));
        this.id.setCellValueFactory(new PropertyValueFactory<ProductoFactura, Integer>("id"));
        this.product.setCellValueFactory(new PropertyValueFactory<ProductoFactura, String>("producto"));
        this.quantity.setCellValueFactory(new PropertyValueFactory<ProductoFactura, Integer>("cantidad"));
        this.price.setCellValueFactory(new PropertyValueFactory<ProductoFactura, Double>("precioUnitario"));
        this.totalPrice.setCellValueFactory(new PropertyValueFactory<ProductoFactura, Double>("precioTotal"));
        facturationTable.setItems(listadoProductosFactura);
    }

    public ProductoFactura rowSelected() {
        return facturationTable.getSelectionModel().getSelectedItem();
    }

    public void searchClient() {
        String clientNit = nitClientInput.getText();
        String clientName = database.service.ClienteService.listClientNit(clientNit);

        if (clientName.length() > 0) {
            nameClientInput.setText(clientName);
            sellerUser.setText("admin");
            serialNumber.setText(serial);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Cliente no encontrado");
            alert.showAndWait();
        }
    }

    public void searchClientClicked(MouseEvent mouseEvent) {
        searchClient();
    }

    public void searchClientEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            searchClient();
        }
    }

    public void searchProduct() {
        Integer productId = Integer.parseInt(idProductInput.getText());
        Producto producto = database.service.ProductoService.listProductByID(productId);
        if (producto.getNombre() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Producto no encontrado");
            alert.showAndWait();
        } else {
            Integer stock = producto.getStock();
            nameProductInput.setText(producto.getNombre());
            priceInput.setText(Double.toString(producto.getPrecio()));
            stockInput.setText(Integer.toString(producto.getStock()));
            enableButtonAddToInvoice(stock);
        }
    }

    public void searchProductClicked(MouseEvent mouseEvent) {
        searchProduct();
    }

    public void searchProducttEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            searchProduct();
        }
    }

    public void onDeleteButtonClicked(MouseEvent mouseEvent) {
        ProductoFactura productoFactura = rowSelected();
        deleteProduct(productoFactura);
    }

    public void addProductToInvoice() {
        try {
            ProductoFactura productoFactura = generateProductoFacturaObject();
            int cantidad = Integer.parseInt(quantityInput.getText());
            Integer stock = Integer.parseInt(stockInput.getText()) - cantidad;
            database.service.VentaService.updateStock(stock, productoFactura.getId());
            listadoProductosFactura.add(productoFactura);
            facturationTable.refresh();
            emptyFields();
            calculateTotalToPay();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e + " Verifique la cantidad de " +
                    "productos agregados a la factura");
            alert.showAndWait();
        }
    }

    public void addProductToInvoiceClic(MouseEvent mouseEvent) {
        addProductToInvoice();
    }

    public void addProductToInvoiceEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            addProductToInvoice();
        }
    }

    public void deleteProduct(ProductoFactura productoFactura) {
        int idProduct = productoFactura.getId();
        Integer stock = database.service.VentaService.availableProduct(idProduct);
        int newStock = productoFactura.getCantidad() + stock;
        database.service.VentaService.updateStock(newStock, idProduct);
        listadoProductosFactura.remove(productoFactura);
    }

    public void cancelInvoice(MouseEvent mouseEvent) {
        try {
            for (ProductoFactura productoFactura : listadoProductosFactura) {
                int idProduct = productoFactura.getId();
                Integer stock = database.service.VentaService.availableProduct(idProduct);
                int newStock = productoFactura.getCantidad() + stock;
                database.service.VentaService.updateStock(newStock, idProduct);
                facturationTable.refresh();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Info");
                alert.setContentText("Factura cancelada con exito");
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e + " Error al cancelar la factura" +
                    "database.service.VentaProductoService.saveBill(ventaProducto)");
            alert.showAndWait();
        }
    }

    public void generateInvoice(){
        try {
            Venta venta = generateVentaObject();
            venta.setSerieVenta(serial);
            database.service.VentaService.createSale(venta);
            try {
                List<VentaProducto> ventaProductos = generateVentaProductoObjects();
                database.service.VentaProductoService.saveBill(ventaProductos);
            } catch (NumberFormatException exception) {
                Alert secondAlert = new Alert(Alert.AlertType.ERROR);
                secondAlert.setHeaderText(null);
                secondAlert.setTitle("Error");
                secondAlert.setContentText(exception + " Factura no generada" +
                        "database.service.VentaProductoService.saveBill(ventaProducto)");
                secondAlert.showAndWait();
            }
        } catch (NumberFormatException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(exception + " Factura no generada" +
                    "database.service.VentaService.createSale(venta)");
            alert.showAndWait();
        }
    }


    public void generateInvoiceOnClic() {
        String methodToPay = payMethod.getSelectionModel().getSelectedItem().toLowerCase();
        System.out.println("method to pay" + methodToPay);
        if (methodToPay.equals("cuotas")){
            generateInvoice();
            SaldoPendiente saldoPendiente = generateSaldoPendienteObjets();
            database.service.SaldoPendienteService.createDobter(saldoPendiente);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.setContentText("Venta generada con exito");
            alert.showAndWait();
        } else if (methodToPay.equals("tarjeta") || methodToPay.equals("efectivo")){
            generateInvoice();
        }
    }

    public ProductoFactura generateProductoFacturaObject() {
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
        totalToPayInput.setText("Q" + totalToPay);
    }

    public List<VentaProducto> generateVentaProductoObjects() {
        List<VentaProducto> ventaProductos = new ArrayList<>();
        for (ProductoFactura productoFactura : listadoProductosFactura) {
            VentaProducto ventaProducto = new VentaProducto();
            ventaProducto.setSerieVenta(serial);
            ventaProducto.setIdProducto(productoFactura.getId());
            ventaProducto.setCantidad(productoFactura.getCantidad());
            ventaProducto.setPrecioVenta(productoFactura.getPrecioTotal());
            ventaProductos.add(ventaProducto);
        }
        return ventaProductos;
    }

    public Venta generateVentaObject() {
        Venta venta = new Venta();
        venta.setNitCliente(nitClientInput.getText());
        venta.setUserVendedor(sellerUser.getText());
        venta.setMonto(totalToPay);
        venta.setMetodoPago(payMethod.getSelectionModel().getSelectedItem().toLowerCase());
        return venta;
    }

    public void enableButtonAddToInvoice(Integer stock) {
        if (stock > 0) {
            addButton.setDisable(false);
        }
    }

    public SaldoPendiente generateSaldoPendienteObjets(){
        SaldoPendiente saldoPendiente = new SaldoPendiente();
        saldoPendiente.setNitClient(nitClientInput.getText());
        saldoPendiente.setNombreCliente(nameClientInput.getText());
        saldoPendiente.setTotalPagar(totalToPay);
        saldoPendiente.setSerieVenta(serial);
        saldoPendiente.setDeudaPendiente(totalToPay);
        saldoPendiente.setAbono(0.0);
        saldoPendiente.setTipoPago(payMethod.getSelectionModel().getSelectedItem().toLowerCase());
        return saldoPendiente;
    }


}

