module com.example.paisesdataset {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;


    opens com.example.paisesdataset to javafx.fxml;
    exports com.example.paisesdataset;
    exports com.example.paisesdataset.Domain;
}