package UI;

import database.dao.VentaDao;
import database.models.Proveedor;
import database.models.Venta;
import database.models.VentaProducto;
import database.service.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("scenes/layout.fxml"));
        Scene scene = new Scene(root,1150,800);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
//        Venta venta = new Venta();
//        venta.setMetodoPago("Efectivo");
//        venta.setNombreVendedor("Vendedor 2");
//        venta.setNombreCliente("Deudor 2");
//        venta.setMonto(0.0);
//        VentaService.createSale(venta);
    }
}
