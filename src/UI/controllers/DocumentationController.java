package UI.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class DocumentationController implements Initializable {

    @FXML
    private WebView web;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final WebEngine webEngine = web.getEngine();
        String newUrl = "https://dounder.github.io/bootstrap4";
        webEngine.load(newUrl);
    }
}
