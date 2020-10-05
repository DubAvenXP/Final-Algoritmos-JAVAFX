package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
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
}
