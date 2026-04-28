module com.ejemplo.listadetareas {
    requires javafx.controls;
    requires javafx.fxml;
    opens com.ejemplo.listadetareas to javafx.fxml;
    exports com.ejemplo.listadetareas;
}
