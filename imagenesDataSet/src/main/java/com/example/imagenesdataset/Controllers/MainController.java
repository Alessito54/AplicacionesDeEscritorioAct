
package com.example.imagenesdataset.Controllers;

import com.example.imagenesdataset.Domain.Imagen;
import com.example.imagenesdataset.data.ConsumidorAPI;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class MainController {

    @FXML
    private FlowPane contenedorImagenes;

    @FXML
    private Label welcomeText;

    @FXML
    public void initialize() {
        cargarTodo();
    }

    public void cargarTodo() {

        new Thread(() -> {

            // 1. Consumir API
            List<Imagen> lista = ConsumidorAPI.cargarDatosList();


            Platform.runLater(() -> {
                cargarImagenes(lista);
            });

        }).start();
    }
    public void cargarImagenes(List<Imagen> list) {

        contenedorImagenes.getChildren().clear();

        for (Imagen image : list){

            // Imagen
            ImageView imageView = new ImageView();
            imageView.setImage(new Image(image.getUrl(), true));
            imageView.setFitWidth(180);
            imageView.setFitHeight(120);
            imageView.setPreserveRatio(true);

            // Autor (como título del producto)
            Label autor = new Label(image.getAutor());
            autor.setWrapText(true);
            autor.setMaxWidth(180);

            // Tarjeta
            VBox tarjeta = new VBox();
            tarjeta.setSpacing(8);
            tarjeta.setPrefWidth(200);
            tarjeta.getChildren().addAll(imageView, autor);

            // Estilo tipo card
            tarjeta.setStyle("""
            -fx-background-color: white;
            -fx-padding: 10;
            -fx-background-radius: 10;
            -fx-border-radius: 10;
            -fx-border-color: #ddd;
        """);

            // Hover (efecto ML 😏)
            tarjeta.setOnMouseEntered(e -> {
                tarjeta.setStyle("""
                -fx-background-color: #f5f5f5;
                -fx-padding: 10;
                -fx-background-radius: 10;
                -fx-border-radius: 10;
                -fx-border-color: #bbb;
            """);
            });

            tarjeta.setOnMouseExited(e -> {
                tarjeta.setStyle("""
                -fx-background-color: white;
                -fx-padding: 10;
                -fx-background-radius: 10;
                -fx-border-radius: 10;
                -fx-border-color: #ddd;
            """);
            });

            contenedorImagenes.getChildren().add(tarjeta);
        }
    }

}
