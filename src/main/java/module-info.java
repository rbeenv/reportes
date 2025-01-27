module com.reportesapp.reportes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires net.sf.jasperreports.core;


    opens com.reportesapp.reportes to javafx.fxml;
    exports com.reportesapp.reportes;
}