package com.ejemplo.listadetareas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ListaDeTareasController {

    @FXML
    private TextField txtTarea;

    @FXML
    private ListView<String> listaTareas;

    @FXML
    private Label lblContador;

    private final ObservableList<String> tareas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listaTareas.setItems(tareas);
        actualizarContador();
    }

    @FXML
    private void handleAgregar() {
        String tarea = txtTarea.getText().trim();
        if (!tarea.isEmpty()) {
            tareas.add(tarea);
            txtTarea.clear();
            actualizarContador();
        }
    }

    @FXML
    private void handleEliminar() {
        int indice = listaTareas.getSelectionModel().getSelectedIndex();
        if (indice >= 0) {
            tareas.remove(indice);
            actualizarContador();
        } else {
            mostrarAlerta("Seleccione una tarea para eliminar.");
        }
    }

    @FXML
    private void handleMarcarCompletada() {
        int indice = listaTareas.getSelectionModel().getSelectedIndex();
        if (indice >= 0) {
            String tarea = tareas.get(indice);
            if (!tarea.startsWith("✔ ")) {
                tareas.set(indice, "✔ " + tarea);
            }
        } else {
            mostrarAlerta("Seleccione una tarea para marcarla como completada.");
        }
    }

    @FXML
    private void handleLimpiarTodo() {
        tareas.clear();
        actualizarContador();
    }

    private void actualizarContador() {
        lblContador.setText("Total de tareas: " + tareas.size());
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Aviso");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
