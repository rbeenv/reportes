package com.reportesapp.reportes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ControladorMain {

    @FXML
    protected void pedidos() throws IOException {
        cambiarEscena("/com/reportesapp/reportes/pedidos.fxml", "Informe Pedidos");
    }

    @FXML
    protected void clientes() throws IOException {
        cambiarEscena("/com/reportesapp/reportes/clientes.fxml", "Informe Clientes");
    }

    private void cambiarEscena(String fxml, String titulo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle(titulo);
        stage.setScene(new Scene(root));
        stage.show();
    }
}