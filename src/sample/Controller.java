package sample;

import database.models.Cliente;
import database.models.Producto;
import database.models.Proveedor;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import static database.service.ClienteService.listClientId;


public class Controller {

    /*Arrows nav manu*/
    @FXML private ImageView facturationArrow;
    @FXML private ImageView inventaryArrow;
    @FXML private ImageView reportArrow;
    @FXML private ImageView stadisticsArrow;
    @FXML private ImageView adminArrow;

    /*Panels nav menu*/
    @FXML private AnchorPane facturationPanel;
    @FXML private AnchorPane inventaryPanel;
    @FXML private AnchorPane reportPanel;
    @FXML private AnchorPane stadisticsPanel;
    @FXML private AnchorPane adminPanel;

    /*Admin Panels*/
    @FXML private AnchorPane menuAdminPanel;

    /*Admin clients Panels*/
    @FXML private AnchorPane createClientPanel;
    @FXML private AnchorPane updateDeleteClientPanel;
    @FXML private AnchorPane clientAdminMenu;

    /*Admin Create Clients Variables*/
    @FXML private TextField idCCInput;
    @FXML private TextField nitCCInput;
    @FXML private TextField nameCCInput;
    @FXML private TextField lastNameCCInput;
    @FXML private TextField directionCCInput;
    @FXML private TextField phoneCCInput;

    /*Admin client update - delete*/
    @FXML private TextField nitSearchUpdateDeleteClient;
    @FXML private TextField idUpdateDeleteClient;
    @FXML private TextField nameUpdateDeleteClient;
    @FXML private TextField lastNameUpdateDeleteClient;
    @FXML private TextField directionUpdateDeleteClient;
    @FXML private TextField phoneUpdateDeleteClient;
    @FXML private TextField nitUpdateDeleteClient;

    /*Admin Providers Panels*/
    @FXML private AnchorPane providersAdminMenu;
    @FXML private AnchorPane createProvidersPanel;
    @FXML private AnchorPane updateDeleteProviderPanel;

    /*Admin create Providers*/
    @FXML private TextField idCPInput;
    @FXML private TextField nameCPInput;
    @FXML private TextArea descriptionCPInput;

    /*Admin update - delete Providers*/
    @FXML private TextField udProviderSearch;
    @FXML private TextField idUDProviderInput;
    @FXML private TextField nameUDProviderInput;
    @FXML private TextArea descriptionUDProviderInput;

    /*Admin Products Panels*/
    @FXML private AnchorPane productsAdminMenu;
    @FXML private AnchorPane createProductsPanel;
    @FXML private AnchorPane updateDeleteProductsPanel;

    /*Admin create Products */
    @FXML private TextField idCProdInput;
    @FXML private TextField nameCProdInput;
    @FXML private TextField priceCProdInput;
    @FXML private TextField stockCProdInput;
    @FXML private TextArea descriptionCProdInput;
    @FXML private TextField providerIdCProdInput;

    /*Admin update - delete Products*/
    @FXML private TextField idUDProdInput;
    @FXML private TextField nameUDProdInput;
    @FXML private TextField priceUDProdInput;
    @FXML private TextField stockUDProdInput;
    @FXML private TextArea descriptionUDProdInput;
    @FXML private TextField providerIdUDProdInput;
    @FXML private TextField udProdSearchInput;

    /*Menu*/
    public void onFacturationButtonClicked(MouseEvent mouseEvent){
        showPanel(1);
    }
    public void onInventaryButtonClicked(MouseEvent mouseEvent){
        showPanel(2);
    }
    public void onReportButtonClicked(MouseEvent mouseEvent){
        showPanel(3);
    }
    public void onStadisticsButtonClicked(MouseEvent mouseEvent){
        showPanel(4);
    }
    public void onAdminButtonClicked(MouseEvent mouseEvent){
        showPanel(5);
    }
    public void onExitButtonClicked(MouseEvent mouseEvent){
        Platform.exit();
        System.exit(0);
    }

    public void showPanel(int panelNumber){
        switch (panelNumber){
            case 1:
                if (facturationPanel.isVisible()){
                    facturationPanel.setVisible(false);
                    facturationArrow.setVisible(false);
                    return;
                }
                facturationPanel.setVisible(true);
                facturationArrow.setVisible(true);
                inventaryPanel.setVisible(false);
                inventaryArrow.setVisible(false);
                reportPanel.setVisible(false);
                reportArrow.setVisible(false);
                stadisticsPanel.setVisible(false);
                stadisticsArrow.setVisible(false);
                adminPanel.setVisible(false);
                adminArrow.setVisible(false);
                break;
            case 2:
                if (inventaryPanel.isVisible()){
                    inventaryPanel.setVisible(false);
                    inventaryArrow.setVisible(false);
                    return;
                }
                facturationPanel.setVisible(false);
                facturationArrow.setVisible(false);
                inventaryPanel.setVisible(true);
                inventaryArrow.setVisible(true);
                reportPanel.setVisible(false);
                reportArrow.setVisible(false);
                stadisticsPanel.setVisible(false);
                stadisticsArrow.setVisible(false);
                adminPanel.setVisible(false);
                adminArrow.setVisible(false);
                break;
            case 3:
                if (reportPanel.isVisible()){
                    reportPanel.setVisible(false);
                    reportArrow.setVisible(false);
                    return;
                }
                inventaryPanel.setVisible(false);
                inventaryArrow.setVisible(false);
                facturationPanel.setVisible(false);
                facturationArrow.setVisible(false);
                reportPanel.setVisible(true);
                reportArrow.setVisible(true);
                stadisticsPanel.setVisible(false);
                stadisticsArrow.setVisible(false);
                adminPanel.setVisible(false);
                adminArrow.setVisible(false);
                break;
            case 4:
                if (stadisticsPanel.isVisible()){
                    stadisticsPanel.setVisible(false);
                    stadisticsArrow.setVisible(false);
                    return;
                }
                inventaryPanel.setVisible(false);
                inventaryArrow.setVisible(false);
                facturationPanel.setVisible(false);
                facturationArrow.setVisible(false);
                reportPanel.setVisible(false);
                reportArrow.setVisible(false);
                stadisticsPanel.setVisible(true);
                stadisticsArrow.setVisible(true);
                adminPanel.setVisible(false);
                adminArrow.setVisible(false);
                break;
            case 5:
                if (adminPanel.isVisible()){
                    adminPanel.setVisible(false);
                    adminArrow.setVisible(false);
                    return;
                }
                inventaryPanel.setVisible(false);
                inventaryArrow.setVisible(false);
                facturationPanel.setVisible(false);
                facturationArrow.setVisible(false);
                reportPanel.setVisible(false);
                reportArrow.setVisible(false);
                stadisticsPanel.setVisible(false);
                stadisticsArrow.setVisible(false);
                adminPanel.setVisible(true);
                adminArrow.setVisible(true);
                break;
        }
    }


    /*Admin Clients*/
    public void onAdminClientButtonClicked(MouseEvent mouseEvent){
        menuAdminPanel.setVisible(false);
        clientAdminMenu.setVisible(true);
        createClientPanel.setVisible((true));
    }

    public void onBackAdminClientButtonClicked(MouseEvent mouseEvent){
        menuAdminPanel.setVisible(true);
        clientAdminMenu.setVisible(false);
        createClientPanel.setVisible(false);
        updateDeleteClientPanel.setVisible(false);
    }

    public void onCreateAdminClientButtonClicked(MouseEvent mouseEvent){
        menuAdminPanel.setVisible(false);
        clientAdminMenu.setVisible(true);
        createClientPanel.setVisible(true);
        updateDeleteClientPanel.setVisible(false);
    }

    public void onUpdateDeleteAdminClientButtonClicked(MouseEvent mouseEvent){
        menuAdminPanel.setVisible(false);
        createClientPanel.setVisible(false);
        clientAdminMenu.setVisible(true);
        updateDeleteClientPanel.setVisible(true);
    }

    public void createClient(MouseEvent mouseEvent){
        Cliente cliente = new Cliente();
        cliente.setNit(nitCCInput.getText());
        cliente.setNombre(nameCCInput.getText());
        cliente.setApellido(lastNameCCInput.getText());
        cliente.setDireccion(directionCCInput.getText());
        cliente.setTelefono(phoneCCInput.getText());
        database.service.ClienteService.createClient(cliente);
    }

    public void searchClient(MouseEvent mouseEvent){
        String nit = nitSearchUpdateDeleteClient.getText();
        Cliente cliente = listClientId(nit);
        idUpdateDeleteClient.setText(String.valueOf(cliente.getIdCliente()));
        nameUpdateDeleteClient.setText(cliente.getNombre());
        lastNameUpdateDeleteClient.setText(cliente.getApellido());
        directionUpdateDeleteClient.setText(cliente.getDireccion());
        phoneUpdateDeleteClient.setText(cliente.getTelefono());
        nitUpdateDeleteClient.setText(cliente.getNit());
    }

    public void updateClient(){
        Cliente cliente = new Cliente();
        cliente.setIdCliente(Integer.parseInt(idUpdateDeleteClient.getText()));
        cliente.setNit(nitUpdateDeleteClient.getText());
        cliente.setNombre(nameUpdateDeleteClient.getText());
        cliente.setApellido(lastNameUpdateDeleteClient.getText());
        cliente.setDireccion(directionUpdateDeleteClient.getText());
        cliente.setTelefono(phoneUpdateDeleteClient.getText());
        database.service.ClienteService.updateClient(cliente);
    }

    public void deleteClient(MouseEvent mouseEvent){
        String nit = nitUpdateDeleteClient.getText();
        database.service.ClienteService.deleteClient(nit);
    }


    /*Admin Providers*/
    public void onAdminProviderButtonClicked(MouseEvent mouseEvent){
        menuAdminPanel.setVisible(false);
        providersAdminMenu.setVisible(true);
        createProvidersPanel.setVisible((true));
    }

    public void onBackAdminProviderButtonClicked(MouseEvent mouseEvent){
        menuAdminPanel.setVisible(true);
        providersAdminMenu.setVisible(false);
        createProvidersPanel.setVisible(false);
        updateDeleteProviderPanel.setVisible(false);
    }

    public void onCreateAdminProviderButtonClicked(MouseEvent mouseEvent){
        menuAdminPanel.setVisible(false);
        providersAdminMenu.setVisible(true);
        createProvidersPanel.setVisible(true);
        updateDeleteProviderPanel.setVisible(false);
    }

    public void onUpdateDeleteAdminProviderButtonClicked(MouseEvent mouseEvent){
        menuAdminPanel.setVisible(false);
        createProvidersPanel.setVisible(false);
        providersAdminMenu.setVisible(true);
        updateDeleteProviderPanel.setVisible(true);
    }

    public void createProvider(MouseEvent mouseEvent){
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(nameCPInput.getText());
        proveedor.setDescripcion(descriptionCPInput.getText());
        database.service.ProveedorService.createProvider(proveedor);
    }

    public void searchProvider(MouseEvent mouseEvent){
        int id = Integer.parseInt(udProviderSearch.getText());
        Proveedor proveedor = database.service.ProveedorService.listProviderByID(id);
        idUDProviderInput.setText(String.valueOf(proveedor.getIdProveedor()));
        nameUDProviderInput.setText(proveedor.getNombre());
        descriptionUDProviderInput.setText(proveedor.getDescripcion());
    }

    public void updateProvider(MouseEvent mouseEvent){
        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(Integer.parseInt(idUDProviderInput.getText()));
        proveedor.setNombre(nameUDProviderInput.getText());
        proveedor.setDescripcion(descriptionUDProviderInput.getText());
        database.service.ProveedorService.updateProvider(proveedor);
    }

    public void deleteProvider(MouseEvent mouseEvent){
        int id = Integer.parseInt(idUDProviderInput.getText());
        database.service.ProveedorService.deleteProvider(id);
    }

    /*Admin Products*/
    public void onAdminProductButtonClicked(MouseEvent mouseEvent){
        menuAdminPanel.setVisible(false);
        productsAdminMenu.setVisible(true);
        createProductsPanel.setVisible((true));
    }

    public void onBackAdminProductButtonClicked(MouseEvent mouseEvent){
        menuAdminPanel.setVisible(true);
        productsAdminMenu.setVisible(false);
        createProductsPanel.setVisible(false);
        updateDeleteProductsPanel.setVisible(false);
    }

    public void onCreateAdminProductButtonClicked(MouseEvent mouseEvent){
        menuAdminPanel.setVisible(false);
        productsAdminMenu.setVisible(true);
        createProductsPanel.setVisible(true);
        updateDeleteProductsPanel.setVisible(false);
    }

    public void onUpdateDeleteAdminProductButtonClicked(MouseEvent mouseEvent){
        menuAdminPanel.setVisible(false);
        createProductsPanel.setVisible(false);
        productsAdminMenu.setVisible(true);
        updateDeleteProductsPanel.setVisible(true);
    }

    public void createProduct(MouseEvent mouseEvent){
        Producto producto = new Producto();
        producto.setNombre(nameCProdInput.getText());
        producto.setPrecio(Double.parseDouble(priceCProdInput.getText()));
        producto.setStock(Integer.parseInt(stockCProdInput.getText()));
        producto.setDescripcion(descriptionCProdInput.getText());
        producto.setIdProvider(Integer.parseInt(providerIdCProdInput.getText()));
        database.service.ProductoService.createProduct(producto);
    }

    public void searchProduct(MouseEvent mouseEvent){
        int id = Integer.parseInt(udProdSearchInput.getText());
        Producto producto = database.service.ProductoService.listProductByID(id);
        idUDProdInput.setText(String.valueOf(producto.getIdProducto()));
        nameUDProdInput.setText(producto.getNombre());
        priceUDProdInput.setText(String.valueOf(producto.getPrecio()));
        stockUDProdInput.setText(String.valueOf(producto.getStock()));
        descriptionUDProdInput.setText(producto.getDescripcion());
        providerIdUDProdInput.setText(String.valueOf(producto.getIdProvider()));

        System.out.println(producto.getIdProvider());
    }

    public void updateProduct(MouseEvent mouseEvent){
        Producto producto = new Producto();
        producto.setIdProducto(Integer.parseInt(idUDProdInput.getText()));
        producto.setNombre(nameUDProdInput.getText());
        producto.setPrecio(Double.parseDouble(priceUDProdInput.getText()));
        producto.setStock(Integer.parseInt(stockUDProdInput.getText()));
        producto.setDescripcion(descriptionUDProdInput.getText());
        producto.setIdProvider(Integer.parseInt(providerIdCProdInput.getText()));
        database.service.ProductoService.updateProduct(producto);
    }

    public void deleteProduct(MouseEvent mouseEvent){
        int id = Integer.parseInt(idUDProdInput.getText());
        database.service.ProductoService.deleteProduct(id);
    }
}
