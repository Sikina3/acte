module com.projetanais.acte {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.projetanais.acte to javafx.fxml;
    exports com.projetanais.acte;
}
