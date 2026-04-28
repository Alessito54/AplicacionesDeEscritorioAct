package com.example.ejercicio1.view;

import com.example.ejercicio1.model.Persona;
import com.example.ejercicio1.service.PersonaService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private final PersonaService servicio = new PersonaService();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ejercicio 1 - Guardar objeto con Jackson");

        GridPane grid = new GridPane();
        grid.setHgap(8);
        grid.setVgap(8);
        grid.setPadding(new Insets(10));

        Label lblNombre = new Label("Nombre:");
        TextField txtNombre = new TextField("Ana");

        Label lblEdad = new Label("Edad:");
        TextField txtEdad = new TextField("25");

        Label lblCiudad = new Label("Ciudad:");
        TextField txtCiudad = new TextField("Veracruz");

        Label lblRuta = new Label("Archivo:");
        TextField txtRuta = new TextField("persona.json");

        Button btnGuardar = new Button("Guardar JSON");

        TextArea txtResultado = new TextArea();
        txtResultado.setEditable(false);
        txtResultado.setPrefRowCount(6);
        txtResultado.setPrefColumnCount(40);

        btnGuardar.setOnAction(e -> {
            try {
                Persona persona = new Persona(
                        txtNombre.getText().trim(),
                        Integer.parseInt(txtEdad.getText().trim()),
                        txtCiudad.getText().trim());
                String json = servicio.guardarPersona(persona, txtRuta.getText().trim());
                txtResultado.setText("Objeto guardado correctamente.\n\n" + json);
            } catch (NumberFormatException ex) {
                txtResultado.setText("ERROR: la edad debe ser un numero entero.");
            } catch (Exception ex) {
                txtResultado.setText("ERROR: " + ex.getMessage());
            }
        });

        grid.add(lblNombre, 0, 0);
        grid.add(txtNombre, 1, 0);
        grid.add(lblEdad, 0, 1);
        grid.add(txtEdad, 1, 1);
        grid.add(lblCiudad, 0, 2);
        grid.add(txtCiudad, 1, 2);
        grid.add(lblRuta, 0, 3);
        grid.add(txtRuta, 1, 3);
        grid.add(btnGuardar, 0, 4, 2, 1);
        grid.add(txtResultado, 0, 5, 2, 1);

        primaryStage.setScene(new Scene(grid));
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
