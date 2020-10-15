package UI.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Home {

    @FXML
    BorderPane mainPane;

    @FXML
    public void facturationScreen(MouseEvent mouseEvent){
        FxmlLoader loader = new FxmlLoader();
        Pane view = loader.getPage("facturation");
        mainPane.setCenter(view);
    }

    @FXML
    public void inventaryScreen(MouseEvent mouseEvent){
        FxmlLoader loader = new FxmlLoader();
        Pane view = loader.getPage("inventary");
        mainPane.setCenter(view);
    }


}
