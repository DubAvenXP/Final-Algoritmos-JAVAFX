package UI.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class RepositoryController implements Initializable {
    @FXML
    private WebView web;

    /**
     * Descripcion: Este metodo se ejecuta al inicializar el fxml y es la sobreescritura
     * de un metodo de la interface Initializable
     * carga la pagina web del repositorio https://github.com/DubAvenXP/Final-Algoritmos-JAVAFX
     * */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final WebEngine webEngine = web.getEngine();
        String url = "https://github.com/DubAvenXP/Final-Algoritmos-JAVAFX";
        webEngine.load(url);
    }
}
