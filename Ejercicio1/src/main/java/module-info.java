module com.example.ejercicio1 {
    requires javafx.controls;
    requires com.fasterxml.jackson.databind;

    opens com.example.ejercicio1.model to com.fasterxml.jackson.databind;

    exports com.example.ejercicio1.view;
}
