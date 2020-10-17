package UI.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Home {

    @FXML
    BorderPane mainPane;

    public void facturationScreen(MouseEvent mouseEvent){
        FxmlLoader loader = new FxmlLoader();
        Pane view = loader.getPage("facturation");
        mainPane.setCenter(view);
    }

    public void reportsScreen(MouseEvent mouseEvent){
        FxmlLoader loader = new FxmlLoader();
        Pane view = loader.getPage("reports");
        mainPane.setCenter(view);
    }

    public void stadisticsScreen(MouseEvent mouseEvent){
        FxmlLoader loader = new FxmlLoader();
        Pane view = loader.getPage("stadistics");
        mainPane.setCenter(view);
    }


    public void productsScreen(MouseEvent mouseEvent){
        FxmlLoader loader = new FxmlLoader();
        Pane view = loader.getPage("products");
        mainPane.setCenter(view);
    }

    public void clientsScreen(MouseEvent mouseEvent){
        FxmlLoader loader = new FxmlLoader();
        Pane view = loader.getPage("clients");
        mainPane.setCenter(view);
    }

    public void providersScreen(MouseEvent mouseEvent){
        FxmlLoader loader = new FxmlLoader();
        Pane view = loader.getPage("providers");
        mainPane.setCenter(view);
    }

    public void adminScreen(MouseEvent mouseEvent){
        FxmlLoader loader = new FxmlLoader();
        Pane view = loader.getPage("admin");
        mainPane.setCenter(view);
    }

    public void developerScreen(MouseEvent mouseEvent){
        FxmlLoader loader = new FxmlLoader();
        Pane view = loader.getPage("developer");
        mainPane.setCenter(view);
    }

}
