module com.ejemplo.gestionestudiantes {
    requires javafx.controls;
    requires javafx.fxml;
    opens com.ejemplo.gestionestudiantes to javafx.fxml;
    exports com.ejemplo.gestionestudiantes;
}
