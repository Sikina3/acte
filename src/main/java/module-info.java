module com.projetanais.acte {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jasperreports;

    opens com.projetanais.acte to javafx.fxml;
    exports com.projetanais.acte;
}
