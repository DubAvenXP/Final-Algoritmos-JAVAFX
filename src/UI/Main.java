package UI;

import database.dao.ProductoDao;
import database.dao.SaldoPendienteDao;
import database.dao.VentaDao;
import database.dao.VentaProductoDao;
import database.models.Producto;
import database.models.SaldoPendiente;
import database.models.Venta;
import database.models.VentaProducto;
import database.service.ProductoService;
import database.service.VentaProductoService;
import database.service.VentaService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("scenes/layout.fxml"));
        Scene scene = new Scene(root, 1250, 900);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
