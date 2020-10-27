package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *  La clase Main sobreescribe el metodo start para crear la interfaz
 * */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("scenes/layout.fxml"));
        Scene scene = new Scene(root, 1250, 900);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Metodo main: punto de entrada de la aplicacion, que inicializa la app
     * */
    public static void main(String[] args) {
        launch(args);
    }
}
