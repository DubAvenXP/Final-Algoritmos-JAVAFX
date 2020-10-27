package UI.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import java.net.URL;

public class FxmlLoader {
    private Pane view;

    /**
     * Descripcion: este metodo hace que la vista de la aplicacion sea dinamica ya que genera
     * un objeto del Tipo pane que corresponde a una nueva vista
     * @param fileName este metodo recibe como parametro el nombre del archivo fxml que se desea cargar
     * @return pane retorna un objeto del tipo Pane correspondiente al archivo cargado
     * */
    public Pane getPage(String fileName) {
        try {
            URL fileUrl = UI.Main.class.getResource("/UI/scenes/"
                    + fileName + ".fxml");
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException("FXML file cant be found");
            }
            view = new FXMLLoader().load(fileUrl);
        } catch (Exception e) {
            System.out.println("No page " + fileName + " please check FxmlLoader.");
        }
        return view;

    }
}
