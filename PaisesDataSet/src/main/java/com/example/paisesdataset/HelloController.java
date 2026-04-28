package com.example.paisesdataset;

import com.example.paisesdataset.Data.Api;
import com.example.paisesdataset.Domain.Pais;
import com.example.paisesdataset.Domain.ZonaTiempo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.List;

import static com.example.paisesdataset.Data.Api.obtenerListaPaises;

public class HelloController {

    @FXML
    private ComboBox<String> boxPaises;

    @FXML
    private Label welcomeText;


    @FXML
    private Button buttonBuscar;
    @FXML
    public void initialize() {
        try {
            List<Pais> lista = obtenerListaPaises();

            for (Pais pais : lista) {
                boxPaises.getItems().add(pais.getName().getCommon());
            }

        } catch (Exception e) {
            System.out.println("Error cargando países: " + e.getMessage());
        }
    }

    @FXML
    void buscar(javafx.event.ActionEvent event) {

        String seleccionado = boxPaises.getValue();

        if (seleccionado != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("pais.fxml"));
                Parent root = loader.load();


                PaisController controller = loader.getController();
                controller.setPais(seleccionado);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    }


