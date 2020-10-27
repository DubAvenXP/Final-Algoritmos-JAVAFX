package UI.controllers;

import UI.models.ProductoFactura;
import database.dao.VentaDao;
import database.models.*;
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
    private TextField totalToPayInput;

    Double totalToPay;

    //variable estatica que genera el serie venta
    public static String serial = VentaDao.generateBillNumber();
    ObservableList<String> payMethods = FXCollections.observableArrayList(
            "Efectivo", "Tarjeta", "Cuotas"
    );


    //Para que las tablas funcionen en la UI se necesitan listas Observables
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

    /**
     * Descripcion: este metodo obtiene la fila seleccionada en la tabla de la UI
     * y lo convierte a un objeto
     * @return ProductoFactura
     * */
    public ProductoFactura rowSelected() {
        return facturationTable.getSelectionModel().getSelectedItem();
    }

    /**
     * Descripcion: Este metodo obtiene los datos del input del nit en la UI
     * y busca la informacion del cliente por medio de la clase ClienteService para mandarla
     * a los input requeridos
     * */
    public void searchClient() {
        String clientNit = nitClientInput.getText();
        Cliente cliente = database.service.ClienteService.listClientNit(clientNit);

        if (cliente.getNombre().length() > 0) {
            nameClientInput.setText(cliente.getNombre() + " " + cliente.getApellido());
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

    /**
     * Descripcion: Este metodo ejecuta el metodo searchClient()
     * cuando se presiona el boton "buscar cliente" en la UI
     * */
    public void searchClientClicked(MouseEvent mouseEvent) {
        searchClient();
    }

    /**
     * Descripcion: Este metodo ejecuta el metodo searchClient()
     * cuando se presiona la tecla ENTER en el textField nitClientInput
     * */
    public void searchClientEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            searchClient();
        }
    }

    /**
     * Descripcion: Este metodo obtiene los datos del input del idProduct en la UI
     * y busca la informacion del producto por medio de la clase ProductoService para mandarla
     * a los input requeridos
     * */
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

    /**
     * Descripcion: Este metodo ejecuta el metodo searchProduct()
     * cuando se presiona el boton "buscar producto" en la UI
     * */
    public void searchProductClicked(MouseEvent mouseEvent) {
        searchProduct();
    }

    /**
     * Descripcion: Este metodo ejecuta el metodo searchProduct()
     * cuando se presiona la tecla ENTER en el textField idProduct
     * */
    public void searchProductEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            searchProduct();
        }
    }

    /**
     * Descripcion: este metodo ejecuta el metodo rowSelected para
     * obtener un Objeto product factura. El objeto que obtiene
     * lo elimina ejecutando el metodo DeleteProduct
     * */
    public void onDeleteButtonClicked(MouseEvent mouseEvent) {
        ProductoFactura productoFactura = rowSelected();
        deleteProduct(productoFactura);
    }

    /**
     * Descripcion: Este metodo obtiene toda la informacion que necesita a traves de los textFields
     * de la UI y la translada a la tabla añadiendolos a la lista observable listadoProductosFactura
     * cada vez que añade un producto se comunica con la clase VentaService para actualizar
     * el stock. Por ultimo actualiza la variable totalToPay para asi mostrar constantemente la cantidad
     * a pagar
     * */
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

    /**
     * Descripcion: Este metodo ejecuta el metodo addProductToInvoice() cada vez que se presiona
     * el boton "agregar" en la UI para agregar productos a la factura
     * */
    public void addProductToInvoiceClic(MouseEvent mouseEvent) {
        addProductToInvoice();
    }

    /**
     * Descripcion: Este metodo ejecuta el metodo addProductToInvoice() cada vez que se presiona
     * la tecla "ENTER" en el textField cantidad en la UI para agregar productos a la factura
     * */
    public void addProductToInvoiceEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            addProductToInvoice();
        }
    }

    /**
     * Descripcion: Este metodo elimina productos de la lista observable listadoProductosFactura
     * y actualiza el stock para devolverlo a su estado inicial
     * @param productoFactura es un objeto de la clase ProductoFactura se debe obtener de la
     *                        lista observable para poder eliminar el producto
     * */
    public void deleteProduct(ProductoFactura productoFactura) {
        int idProduct = productoFactura.getId();
        Integer stock = database.service.VentaService.availableProduct(idProduct);
        int newStock = productoFactura.getCantidad() + stock;
        database.service.VentaService.updateStock(newStock, idProduct);
        listadoProductosFactura.remove(productoFactura);
    }

    /**
     * Descripcion: Este metodo elimina todos los objetos del observable lista y actualiza el stock
     * a su estado inicial
     * */
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

    /**
     * Descripcion: este metodo genera una factura y genera un venta
     * */
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

    /**
     * Descripcion: este metodo ejecuta el metodo generateInvoice generando una factura y generado un venta
     * cada vez que se presiona el boton generar factura en la UI
     * */
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

    /**
     * Descripcion: este metodo genera un objeto ProductoFactura por medio de las variables disponibles en la UI
     * @return ProductoFactura retorna un objeto de la clase ProductoFactura
     * */
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

    /**
     *Descripcion: este metodo vacia las textFields de la UI para que le usuario siga introduciendo datos
     * */
    public void emptyFields() {
        idProductInput.setText("");
        nameProductInput.setText("");
        quantityInput.setText("");
        priceInput.setText("");
        stockInput.setText("");
    }

    /**
     * Descripcion: este metodo actualiza la variable de scope global totalToPay para actualizar constantemente
     * el textField de total a pagar constantemente
     * */
    public void calculateTotalToPay() {
        totalToPay = 0.0;
        for (ProductoFactura productoFactura : listadoProductosFactura) {
            totalToPay += productoFactura.getPrecioTotal();
        }
        totalToPayInput.setText("Q" + totalToPay);
    }

    /**
     * Descripcion: este metodo convierte la lista observable que es del tipo ProductoFactura a una lista
     * de objetos VentaProducto
     * @return List del tipo VentaProducto
     * */
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

    /**
     * Descripcion: Con la informacion disponible en la UI este metodo genera un objeto Venta
     * @return venta
     * */
    public Venta generateVentaObject() {
        Venta venta = new Venta();
        venta.setNitCliente(nitClientInput.getText());
        venta.setUserVendedor(sellerUser.getText());
        venta.setMonto(totalToPay);
        venta.setMetodoPago(payMethod.getSelectionModel().getSelectedItem().toLowerCase());
        return venta;
    }

    /**
     * Descripcion: Cuando se busca un producto, este metodo detecta si el stock es cero
     * si este es igual a cero deshabilita el boton para agregar producto a factura
     * */
    public void enableButtonAddToInvoice(Integer stock) {
        if (stock > 0) {
            addButton.setDisable(false);
        }
    }

    /**
     * Descripcion: este metodo genera objetos del tipo SaldoPendiente
     * @return SaldoPendiente
     * */
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

