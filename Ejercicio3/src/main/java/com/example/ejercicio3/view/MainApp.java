package com.example.ejercicio3.view;

import com.example.ejercicio3.model.Persona;
import com.example.ejercicio3.service.PersonaService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainApp extends Application {

    private final PersonaService servicio = new PersonaService();
    private static final String ARCHIVO = "personas.json";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ejercicio 3 - JSON Manual (sin librerias)");

        TextArea txtResultado = new TextArea();
        txtResultado.setEditable(false);
        txtResultado.setPrefRowCount(12);
        txtResultado.setPrefColumnCount(45);

        Button btnGuardar = new Button("Construir y guardar JSON manualmente");
        btnGuardar.setOnAction(e -> {
            try {
                List<Persona> lista = new ArrayList<>();
                lista.add(new Persona("Ana", 25, "Veracruz"));
                lista.add(new Persona("Luis", 30, "CDMX"));
                lista.add(new Persona("Maria", 22, "Puebla"));

                String json = servicio.guardarListaManual(lista, ARCHIVO);
                txtResultado.setText("Archivo guardado: " + ARCHIVO + "\n\nContenido:\n" + json);
            } catch (Exception ex) {
                txtResultado.setText("ERROR: " + ex.getMessage());
            }
        });

        VBox root = new VBox(8,
                new Label("Genera el JSON caracter por caracter usando FileWriter."),
                new Label("Sin Jackson ni ninguna otra libreria externa."),
                btnGuardar,
                txtResultado);
        root.setPadding(new Insets(10));

        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
