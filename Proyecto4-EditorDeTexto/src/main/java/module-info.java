module com.ejemplo.editardetexto {
    requires javafx.controls;
    requires javafx.fxml;
    opens com.ejemplo.editardetexto to javafx.fxml;
    exports com.ejemplo.editardetexto;
}
