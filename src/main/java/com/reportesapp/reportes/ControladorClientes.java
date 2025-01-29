package com.reportesapp.reportes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ControladorClientes {

    @FXML
    private TextField clienteTextField; // Campo de texto para el ID del cliente
    @FXML
    private ComboBox<String> estadoComboBox;
    @FXML
    private Button confirmarButton;
    @FXML
    private ImageView logoImage;

    @FXML
    public void initialize() {
        // Cargar la imagen del logo
        logoImage.setImage(new Image(getClass().getResource("/images/imagenLogoMensajeria.png").toExternalForm()));

        // Rellenar el ComboBox con datos
        estadoComboBox.getItems().addAll("Completado", "Pendiente", "Cancelado");

        confirmarButton.setDefaultButton(true);
    }

    @FXML
    protected void generarInforme() {
        String cliente = clienteTextField.getText(); // Obtener el ID del cliente del campo de texto
        String estado = estadoComboBox.getValue();

        if (cliente == null || cliente.isEmpty() || estado == null) {
            System.out.println("Por favor, seleccione ambos valores.");
            return;
        }

        try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/report_db", "ruben", "arrullo")) {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("Cliente", Integer.parseInt(cliente)); // Convertir cliente a Integer
            parametros.put("Estado", estado);

            System.out.println("Parámetros establecidos: " + parametros);

            JasperPrint print = JasperFillManager.fillReport("src/main/resources/reportes/PedidosPorClienteYEstado.jasper", parametros, conexion);

            String outputFilePath = "Informe_Pedidos_Cliente_Estado.pdf";
            JasperExportManager.exportReportToPdfFile(print, outputFilePath);

            System.out.println("Informe generado correctamente en: " + outputFilePath);
        } catch (SQLException e) {
            System.out.println("Error de conexión con la base de datos: " + e.getMessage());
        } catch (JRException e) {
            System.out.println("Error al generar el informe con JasperReports: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("El ID del cliente debe ser un número entero: " + e.getMessage());
        }
    }
}