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


public class LayoutController {

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

    //Informacion del usuario
    private String user;
    private String password;
    FxmlLoader loader = new FxmlLoader(); //Instancia de la clase FxmlLoader para cargar distintos modulos
    Node loginView; //Variable utilizada para almacenar la vista de inicio de sesion

    /**
     * Descripcion: Metodo utilizado para obtener la informacion del usuario proveniente de los textFields
     * */
    public void getUserInfo(){
        user = userInput.getText();
        password = passwordInput.getText();
    }

    /**
     * Descripcion: Metodo utilizado validar la informacion de inicio de sesion y cargar la pantalla del home
     * */
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

    /**
     * Descripcion: metodo que internamente ejecuta el metodo login() cuando el boton login es presionado
     * */
    public void loginButton(MouseEvent mouseEvent){
        login();
    }

    /**
     * Descripcion: metodo que internamente ejecuta el metodo login() cuando la tecla enter es presionada
     * en el textField contraseña y cuando se presiona la tecla enter al boton
     * */
    public void loginEnter(KeyEvent keyEvent){
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            login();
        }
    }

    /**
     * Descripcion: metodo que sale de la sesion iniciada por el usuario cuando el icono de exit es presionado
     * */
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

    /**
     * Descripcion: metodo que carga la ventana de facturacion
     * */
    public void facturationScreen(MouseEvent mouseEvent){
        Pane facturation = loader.getPage("facturation");
        mainPane.setCenter(facturation);
    }

    /**
     * Descripcion: metodo que carga la ventana de reportes
     * */
    public void reportsScreen(MouseEvent mouseEvent){
        Pane view = loader.getPage("reports");
        mainPane.setCenter(view);
    }

    /**
     * Descripcion: metodo que carga la ventana de estadisticas
     * */
    public void stadisticsScreen(MouseEvent mouseEvent){
        Pane view = loader.getPage("stadistics");
        mainPane.setCenter(view);
    }

    /**
     * Descripcion: metodo que carga la ventana de productos
     * */
    public void productsScreen(MouseEvent mouseEvent){
        Pane view = loader.getPage("products");
        mainPane.setCenter(view);
    }

    /**
     * Descripcion: metodo que carga la ventana de clientes
     * */
    public void clientsScreen(MouseEvent mouseEvent){
        Pane view = loader.getPage("clients");
        mainPane.setCenter(view);
    }

    /**
     * Descripcion: metodo que carga la ventana de proveedores
     * */
    public void providersScreen(MouseEvent mouseEvent){
        Pane view = loader.getPage("providers");
        mainPane.setCenter(view);
    }

    /**
     * Descripcion: metodo que carga la ventana del home
     * */
    public void homeScreen(MouseEvent mouseEvent){
        Pane view = loader.getPage("home");
        mainPane.setCenter(view);
    }

    /**
     * Descripcion: metodo que carga la ventana de documentacion
     * */
    public void documentationScreen(MouseEvent mouseEvent){
        Pane view = loader.getPage("documentation");
        mainPane.setCenter(view);
    }

    /**
     * Descripcion: metodo que carga la ventana del repositorio
     * */
    public void repositoryController(MouseEvent mouseEvent){
        Pane view = loader.getPage("repository");
        mainPane.setCenter(view);
    }

}
