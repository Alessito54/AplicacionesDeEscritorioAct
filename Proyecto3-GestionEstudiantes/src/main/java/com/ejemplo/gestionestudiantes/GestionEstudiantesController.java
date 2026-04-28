package com.ejemplo.gestionestudiantes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class GestionEstudiantesController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtMateria;
    @FXML private TextField txtCalificacion;
    @FXML private TableView<Estudiante> tablaEstudiantes;
    @FXML private TableColumn<Estudiante, String> colNombre;
    @FXML private TableColumn<Estudiante, String> colMateria;
    @FXML private TableColumn<Estudiante, Double> colCalificacion;
    @FXML private Label lblPromedio;

    private final ObservableList<Estudiante> estudiantes = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
        colMateria.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMateria()));
        colCalificacion.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getCalificacion()).asObject());
        tablaEstudiantes.setItems(estudiantes);
    }

    @FXML
    private void handleAgregar() {
        String nombre = txtNombre.getText().trim();
        String materia = txtMateria.getText().trim();
        String calStr = txtCalificacion.getText().trim();
        if (nombre.isEmpty() || materia.isEmpty() || calStr.isEmpty()) {
            mostrarAlerta("Por favor complete todos los campos.");
            return;
        }
        try {
            double calificacion = Double.parseDouble(calStr);
            if (calificacion < 0 || calificacion > 10) {
                mostrarAlerta("La calificación debe estar entre 0 y 10.");
                return;
            }
            estudiantes.add(new Estudiante(nombre, materia, calificacion));
            limpiarCampos();
            actualizarPromedio();
        } catch (NumberFormatException e) {
            mostrarAlerta("La calificación debe ser un número válido.");
        }
    }

    @FXML
    private void handleEliminar() {
        Estudiante seleccionado = tablaEstudiantes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            estudiantes.remove(seleccionado);
            actualizarPromedio();
        } else {
            mostrarAlerta("Seleccione un estudiante para eliminar.");
        }
    }

    @FXML
    private void handleLimpiar() {
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtMateria.clear();
        txtCalificacion.clear();
    }

    private void actualizarPromedio() {
        if (estudiantes.isEmpty()) {
            lblPromedio.setText("Promedio general: N/A");
        } else {
            double promedio = estudiantes.stream()
                    .mapToDouble(Estudiante::getCalificacion)
                    .average()
                    .orElse(0);
            lblPromedio.setText(String.format("Promedio general: %.2f", promedio));
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Aviso");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public static class Estudiante {
        private final String nombre;
        private final String materia;
        private final double calificacion;

        public Estudiante(String nombre, String materia, double calificacion) {
            this.nombre = nombre;
            this.materia = materia;
            this.calificacion = calificacion;
        }

        public String getNombre() { return nombre; }
        public String getMateria() { return materia; }
        public double getCalificacion() { return calificacion; }
    }
}
