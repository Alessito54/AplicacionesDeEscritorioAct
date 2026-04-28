module com.example.ejercicio2 {
    requires javafx.controls;
    requires com.fasterxml.jackson.databind;

    opens com.example.ejercicio2.model to com.fasterxml.jackson.databind;

    exports com.example.ejercicio2.view;
}
