module com.example.imagenesdataset {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;

    // Jackson modules
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    // Permite que JavaFX y Jackson accedan a tus modelos y controladores
    opens com.example.imagenesdataset to javafx.fxml, com.fasterxml.jackson.databind;
    opens com.example.imagenesdataset.Controllers to javafx.fxml, com.fasterxml.jackson.databind;
    opens com.example.imagenesdataset.Domain to com.fasterxml.jackson.databind;

    exports com.example.imagenesdataset;
}