package UI;

import database.dao.VentaDao;
import database.models.Proveedor;
import database.models.Venta;
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
//        venta.setNombreVendedor("Vendedor Falso");
//        venta.setNombreCliente("Deudor Falso");
//        venta.setMonto(0.0);
//        venta.setSerieVenta("1234");
//        VentaService.createSale(venta);
//        List<Venta> ventaList = VentaService.balance();
//        for (Venta venta: ventaList) {
//            System.out.println(venta.getSerieVenta());
//            System.out.println(venta.getNombreCliente());
//            System.out.println(venta.getMetodoPago());
//            System.out.println(venta.getMonto());
//            System.out.println(venta.getFechaVenta());
//        }
    }
}
