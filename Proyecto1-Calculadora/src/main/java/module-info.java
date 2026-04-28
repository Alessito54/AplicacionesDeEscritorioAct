module com.ejemplo.calculadora {
    requires javafx.controls;
    requires javafx.fxml;
    opens com.ejemplo.calculadora to javafx.fxml;
    exports com.ejemplo.calculadora;
}
