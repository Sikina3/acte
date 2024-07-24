module com.projetanais.acte {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.projetanais.acte to javafx.fxml;
    exports com.projetanais.acte;
}
