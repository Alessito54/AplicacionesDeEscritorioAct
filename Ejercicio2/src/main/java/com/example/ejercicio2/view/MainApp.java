package com.example.ejercicio2.view;

import com.example.ejercicio2.model.Persona;
import com.example.ejercicio2.service.PersonaService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainApp extends Application {

    private final PersonaService servicio = new PersonaService();
    private static final String ARCHIVO = "personas.json";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ejercicio 2 - Guardar y leer lista con Jackson");

        TextArea txtResultado = new TextArea();
        txtResultado.setEditable(false);
        txtResultado.setPrefRowCount(12);
        txtResultado.setPrefColumnCount(45);

        Button btnGuardar = new Button("Guardar lista en " + ARCHIVO);
        btnGuardar.setOnAction(e -> {
            try {
                List<Persona> personas = new ArrayList<>();
                personas.add(new Persona("Ana", 25, "Veracruz"));
                personas.add(new Persona("Luis", 30, "CDMX"));
                personas.add(new Persona("Maria", 22, "Puebla"));

                String json = servicio.guardarLista(personas, ARCHIVO);
                txtResultado.setText("Lista guardada correctamente.\n\n" + json);
            } catch (Exception ex) {
                txtResultado.setText("ERROR al guardar: " + ex.getMessage());
            }
        });

        Button btnLeer = new Button("Leer " + ARCHIVO);
        btnLeer.setOnAction(e -> {
            try {
                List<Persona> personas = servicio.leerLista(ARCHIVO);
                StringBuilder sb = new StringBuilder("Personas leidas del JSON:\n\n");
                for (Persona p : personas) {
                    sb.append(p.getNombre())
                            .append(" - edad: ").append(p.getEdad())
                            .append(" - ciudad: ").append(p.getCiudad())
                            .append("\n");
                }
                txtResultado.setText(sb.toString());
            } catch (Exception ex) {
                txtResultado.setText("ERROR al leer: " + ex.getMessage()
                        + "\n\nAsegurate de guardar la lista primero.");
            }
        });

        HBox botones = new HBox(8, btnGuardar, btnLeer);

        VBox root = new VBox(8,
                new Label("Personas de ejemplo: Ana/25/Veracruz, Luis/30/CDMX, Maria/22/Puebla"),
                botones,
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
