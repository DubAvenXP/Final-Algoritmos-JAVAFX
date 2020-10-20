package UI.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class FacturationController {
    @FXML
    private TextField idFacturationInput;

    @FXML
    private TextField nitClientInput;

    @FXML
    private TextField idProductInput;

    @FXML
    private TextField priceInput;

    @FXML
    private Spinner<Integer> quantityInput;

    @FXML
    private Button addButton;

    @FXML
    private DatePicker dateInput;

    @FXML
    private TextField nameClientInput;

    @FXML
    private TextField nameProductInput;

    @FXML
    private TextField stockInput;

    @FXML
    private TextField sellerId;

    @FXML
    private TableView<?> facturationTable;

    @FXML
    private TableColumn<?, ?> index;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> product;

    @FXML
    private TableColumn<?, ?> quantity;

    @FXML
    private TableColumn<?, ?> price;

    @FXML
    private TableColumn<?, ?> totalPrice;

    @FXML
    private Button deleteButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button generateButton;

    @FXML
    private Button searchClientButton;

    @FXML
    private Button searchProductButton;

    @FXML
    private TextField totalItemsInput;

    @FXML
    private TextField totalToPayInput;

    public void rowSelected(MouseEvent mouseEvent){

    }

    /*Douglas (podemos usar el clientService el de obtener cliente por nit)
    Buscar clientes, debe retornar nombre nit y nombre*/
    public void searchClientOnClic(MouseEvent mouseEvent){

    }

    /*Douglas (Podemos usar el product service)
     Buscar producto, debe retornar id, nombre, precio unitario y stock del producto, */
    public void searchProductOnClic(MouseEvent mouseEvent){

    }

    /*Douglas el modelo de factura es el que ira en la tabla (mira la tabla de la UI)
    el modelo lleva indice, ID, nombreProducto, cantidad, precio... total
    cada linea es un objeto
     */
    public void addProductToInvoice(MouseEvent mouseEvent){

    }


    public void deleteProduct(MouseEvent mouseEvent){

    }

    public void cancelInvoice(MouseEvent mouseEvent){

    }

    public void generateInvoice(MouseEvent mouseEvent){

    }
}
