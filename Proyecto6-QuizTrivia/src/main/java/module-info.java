module com.ejemplo.quiztrivia {
    requires javafx.controls;
    requires javafx.fxml;
    opens com.ejemplo.quiztrivia to javafx.fxml;
    exports com.ejemplo.quiztrivia;
}
