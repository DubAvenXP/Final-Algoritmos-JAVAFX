package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class Controller {

    @FXML private ImageView facturationArrow;
    @FXML private ImageView inventaryArrow;
    @FXML private ImageView reportArrow;
    @FXML private ImageView stadisticsArrow;
    @FXML private ImageView adminArrow;

    @FXML private AnchorPane facturationPanel;
    @FXML private AnchorPane inventaryPanel;
    @FXML private AnchorPane reportPanel;
    @FXML private AnchorPane stadisticsPanel;
    @FXML private AnchorPane adminPanel;

    @FXML private TextField idCCInput;
    @FXML private TextField nitCCInput;
    @FXML private TextField nameCCInput;
    @FXML private TextField lastNameCCInput;
    @FXML private TextField directionCCInput;
    @FXML private TextField phoneCCInput;


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

    public void crearClientePrueba(MouseEvent event){
        Integer id = Integer.parseInt(idCCInput.getText());
        String nit = nitCCInput.getText();
        String name = nameCCInput.getText();
        String lastName = lastNameCCInput.getText();
        String direction = directionCCInput.getText();
        String phone = phoneCCInput.getText();
        database.service.ClienteService.createClient(
                id, nit, name, lastName, direction, phone);
    }
}
