module com.ejemplo.agendacontactos {
    requires javafx.controls;
    requires javafx.fxml;
    opens com.ejemplo.agendacontactos to javafx.fxml;
    exports com.ejemplo.agendacontactos;
}
