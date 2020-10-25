package UI.controllers;

import UI.utils.CreateExcelFile;
import UI.utils.FileModel;
import UI.utils.HeadersModels;
import database.models.Cliente;
import database.models.Producto;
import database.models.Proveedor;
import database.models.SaldoPendiente;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class ReportController {

    @FXML
    private TextField nitClienteInput;

    @FXML
    private TextField nombreClienteInput;

    @FXML
    private TextField idProductoInput;

    @FXML
    private TextField nombreProductoInput;

    @FXML
    private TextField idProveedorInput;

    @FXML
    private TextField nombreProveedorInput;


    /*
     * Clientes
     * */

    public void exportClients() {
        List<Cliente> clientes = database.service.ClienteService.listClient();
        String[] headers = HeadersModels.clientHeaders;
        FileModel fileModel = new FileModel("Clientes", "Clientes");
        try {
            UI.utils.CreateExcelFile.exportClientsToExcel(clientes, headers, fileModel);
            alertInfoSuccess();
        } catch (Error error) {
            alertError(error);
        }
    }

    public void exportDebtors() {
        List<SaldoPendiente> saldosPendientes = database.service.SaldoPendienteService.viewAllDobter();
        String[] headers = HeadersModels.saldoPendienteHeaders;
        FileModel fileModel = new FileModel("Deudores", "Deudores");
        try {
            UI.utils.CreateExcelFile.exportDebtorCustomersToExcel(saldosPendientes, headers, fileModel);
            alertInfoSuccess();
        } catch (Error error) {
            alertError(error);
        }
    }

    public void exportClient() {
        String nit = nitClienteInput.getText();
        List<Cliente> clientes = new ArrayList<>();
        Cliente cliente = database.service.ClienteService.listClientNit(nit);
        clientes.add(cliente);
        String[] headers = HeadersModels.clientHeaders;
        FileModel fileModel = new FileModel("Clientes", "Clientes");
        try {
            UI.utils.CreateExcelFile.exportClientsToExcel(clientes, headers, fileModel);
            alertInfoSuccess();
        } catch (Error error) {
            alertError(error);
        }
    }

    /*
     * Productos
     * */

    public void exportProducts() {
        List<Producto> productos = database.service.ProductoService.listProduct();
        String[] headers = HeadersModels.productoHeaders;
        FileModel fileModel = new FileModel("Productos", "Productos");
        try {
            UI.utils.CreateExcelFile.exportProductsToExcel(productos, headers, fileModel);
            alertInfoSuccess();
        } catch (Error error) {
            alertError(error);
        }
    }

    public void exportProductsOutOfStock() {
        List<Producto> productos = database.service.ProductoService.stockZero();
        String[] headers = HeadersModels.productoHeaders;
        FileModel fileModel = new FileModel("ProductosSinExistencias", "Sin existencias");
        try {
            UI.utils.CreateExcelFile.exportProductsToExcel(productos, headers, fileModel);
            alertInfoSuccess();
        } catch (Error error) {
            alertError(error);
        }
    }

    public void exportBestSellerProducts() {
        List<Producto> productos = database.service.ProductoService.bestSellers();
        String[] headers = HeadersModels.productoHeaders;
        FileModel fileModel = new FileModel("ProductosMejoresVendidos", "Mejores vendidos");
        try {
            UI.utils.CreateExcelFile.exportProductsToExcel(productos, headers, fileModel);
            alertInfoSuccess();
        } catch (Error error) {
            alertError(error);
        }
    }

    public void exportMoreExpensiveProducts() {
        List<Producto> productos = database.service.ProductoService.highestPrice();
        String[] headers = HeadersModels.productoHeaders;
        FileModel fileModel = new FileModel("ProductosMasCaros", "Prod. mas caros");
        try {
            UI.utils.CreateExcelFile.exportProductsToExcel(productos, headers, fileModel);
            alertInfoSuccess();
        } catch (Error error) {
            alertError(error);
        }
    }

    public void exportCheaperProducts() {
        List<Producto> productos = database.service.ProductoService.lowestPrice();
        String[] headers = HeadersModels.productoHeaders;
        FileModel fileModel = new FileModel("ProductosMasBaratos", "Prod. mas baratos");
        try {
            UI.utils.CreateExcelFile.exportProductsToExcel(productos, headers, fileModel);
            alertInfoSuccess();
        } catch (Error error) {
            alertError(error);
        }
    }

    public void exportProviders() {
        List<Proveedor> proveedores = database.service.ProveedorService.listProvider();
        String[] headers = HeadersModels.proveedorHeaders;
        FileModel fileModel = new FileModel("Proveedores", "listado proveedores");
        try {
            UI.utils.CreateExcelFile.exportProvidersToExcel(proveedores, headers, fileModel);
            alertInfoSuccess();
        } catch (Error error) {
            alertError(error);
        }
    }

    /*
    * Utils
    * */

    public void alertInfoSuccess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Info");
        alert.setContentText("Archivo creado correctamente en la ruta \n" +
                CreateExcelFile.reportPath);
        alert.showAndWait();
    }

    public void alertError(Error error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(error + "ocurrio un error al " +
                "crear el archivo .xls");
        alert.showAndWait();
    }

    public void searchClient(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            String nit = nitClienteInput.getText();
            Cliente cliente = database.service.ClienteService.listClientNit(nit);
            nombreClienteInput.setText(cliente.getNombre() + " " + cliente.getApellido());
        }
    }

    public void searchProduct(KeyEvent keyEvent){
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            int id = Integer.parseInt(idProductoInput.getText());
            Producto producto = database.service.ProductoService.listProductByID(id);
            nombreProductoInput.setText(producto.getNombre());
        }
    }

    public void searchProvider(KeyEvent keyEvent){
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            int id = Integer.parseInt(idProveedorInput.getText());
            Proveedor proveedor = database.service.ProveedorService.listProviderByID(id);
            nombreProductoInput.setText(proveedor.getNombre());
        }
    }


}
