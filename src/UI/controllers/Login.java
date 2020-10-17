package UI.controllers;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;

public class Login {

    @FXML
    BorderPane mainPane;

    @FXML
    private TextField userInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Label loginMessage;

    String user;
    String password;

    public void getUserInfo(){
        user = userInput.getText();
        password = passwordInput.getText();
    }

    public void homeScreen(MouseEvent mouseEvent){
        getUserInfo();
        if (user.equals("admin") && password.equals("admin")){
            FxmlLoader loader = new FxmlLoader();
            Pane view = loader.getPage("home");
            mainPane.setCenter(view);
        }  else {
            loginMessage.setText("Contraseña incorrecta");
        }
    }
}
