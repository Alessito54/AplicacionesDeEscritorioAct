module com.example.crud_mongo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;

    opens com.example.crud_mongo to javafx.fxml;
    opens com.example.crud_mongo.controller to javafx.fxml;
    opens com.example.crud_mongo.model to javafx.base;

    exports com.example.crud_mongo;
}