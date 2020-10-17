package UI.controllers;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;


public class Layout {

    @FXML
    private BorderPane mainPane;

    @FXML
    private TextField userInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Label loginMessage;

    @FXML
    private VBox aside;

    @FXML
    private AnchorPane header;

    String user;
    String password;
    FxmlLoader loader = new FxmlLoader();
    Node loginView;

    public void getUserInfo(){
        user = userInput.getText();
        password = passwordInput.getText();
    }

    public void login(){
        getUserInfo();
        loginView = mainPane.getCenter();
        if (user.equals("admin") && password.equals("admin")){
            Pane main = loader.getPage("home");
            mainPane.setCenter(main);
            aside.setVisible(true);
            aside.setPrefWidth(144.0);
            aside.setPrefHeight(637.0);
            header.setVisible(true);
            header.setPrefWidth(939.0);
            header.setPrefHeight(88.0);
            userInput.setText("");
            passwordInput.setText("");
            mainPane.autosize();
        }  else {
            loginMessage.setText("Error: usuario o contraseña incorrecta, porfavor intente de nuevo");
        }
    }

    public void loginButton(MouseEvent mouseEvent){
        login();
    }

    public void loginEnter(KeyEvent keyEvent){
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            login();
        }
    }

    public void logout(MouseEvent mouseEvent){
        userInput.setText("");
        passwordInput.setText("");
        mainPane.setCenter(loginView);
        aside.setVisible(false);
        aside.setPrefWidth(0.0);
        aside.setPrefHeight(0.0);
        header.setVisible(false);
        header.setPrefWidth(0.0);
        header.setPrefHeight(0.0);

    }

    public void facturationScreen(MouseEvent mouseEvent){
        Pane facturation = loader.getPage("facturation");
        mainPane.setCenter(facturation);
    }

    public void reportsScreen(MouseEvent mouseEvent){
        Pane view = loader.getPage("reports");
        mainPane.setCenter(view);
    }

    public void stadisticsScreen(MouseEvent mouseEvent){
        Pane view = loader.getPage("stadistics");
        mainPane.setCenter(view);
    }

    public void productsScreen(MouseEvent mouseEvent){
        Pane view = loader.getPage("products");
        mainPane.setCenter(view);
    }

    public void clientsScreen(MouseEvent mouseEvent){
        Pane view = loader.getPage("clients");
        mainPane.setCenter(view);
    }

    public void providersScreen(MouseEvent mouseEvent){
        Pane view = loader.getPage("providers");
        mainPane.setCenter(view);
    }

    public void adminScreen(MouseEvent mouseEvent){
        Pane view = loader.getPage("admin");
        mainPane.setCenter(view);
    }

    public void developerScreen(MouseEvent mouseEvent){
        Pane view = loader.getPage("developer");
        mainPane.setCenter(view);
    }

}
