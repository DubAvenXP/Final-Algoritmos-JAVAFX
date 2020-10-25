package UI.controllers;

import UI.utils.CreateExcelFile;
import database.models.Cliente;
import javafx.scene.control.Alert;
import java.io.IOException;
import java.util.List;

public class ReportController {

    public List<Cliente> getListClients() {
        return database.service.ClienteService.listClient();
    }

    public void exportClientsToExcel() throws IOException {
        List<Cliente> clientes = getListClients();
        try {
            UI.utils.CreateExcelFile.exportClientsToExcel(clientes);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.setContentText("Archivo creado correctamente en la ruta \n" +
                    CreateExcelFile.reportPath);
            alert.showAndWait();
        } catch (Error exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(exception + "ocurrio un error al " +
                    "crear el archivo .xls");
            alert.showAndWait();
        }
    }


}
