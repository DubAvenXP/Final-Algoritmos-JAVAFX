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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final WebEngine webEngine = web.getEngine();
        String url = "https://dubavenxp.github.io/proyecto-final-logica/html/sobrenosotros.html";
        webEngine.load(url);
    }
}
