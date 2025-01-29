package com.reportesapp.reportes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tooltip;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ControladorPedidos {

    @FXML
    private Button btnGenerarPedido;
    @FXML
    private DatePicker datePickerInicio;
    @FXML
    private DatePicker datePickerFin;

    @FXML
    public void initialize() {
        Tooltip tooltipFechaInicio = new Tooltip("Selecciona la fecha de inicio");
        Tooltip tooltipFechaFin = new Tooltip("Selecciona la fecha de fin");
        Tooltip tooltipGenerarInforme = new Tooltip("Haz clic para generar el informe");

        datePickerInicio.setTooltip(tooltipFechaInicio);
        datePickerFin.setTooltip(tooltipFechaFin);
        btnGenerarPedido.setTooltip(tooltipGenerarInforme);

        btnGenerarPedido.setDefaultButton(true);
    }

    @FXML
    protected void generarInforme() {
        if (datePickerInicio.getValue() == null || datePickerFin.getValue() == null) {
            System.out.println("Por favor, seleccione ambas fechas.");
            return;
        }

        try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/report_db", "ruben", "arrullo")) {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("FechaInicio", java.sql.Date.valueOf(datePickerInicio.getValue()));
            parametros.put("FechaFin", java.sql.Date.valueOf(datePickerFin.getValue()));

            System.out.println("Parámetros establecidos: " + parametros);

            String reportPath = "src/main/resources/reportes/PedidosPorFecha.jasper"; // Ruta jasper
            JasperPrint print = JasperFillManager.fillReport(reportPath, parametros, conexion);

            String outputFilePath = "Informe_Pedidos_Fecha.pdf";
            JasperExportManager.exportReportToPdfFile(print, outputFilePath);

            System.out.println("Informe generado correctamente en: " + outputFilePath);
        } catch (SQLException e) {
            System.out.println("Error de conexión con la base de datos: " + e.getMessage());
        } catch (JRException e) {
            System.out.println("Error al generar el informe con JasperReports: " + e.getMessage());
        }
    }
}