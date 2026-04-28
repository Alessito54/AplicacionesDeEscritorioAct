package com.ejemplo.editardetexto;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class EditorDeTextoController {

    @FXML private TextArea areaTexto;
    @FXML private Label lblArchivo;
    @FXML private Label lblLineas;

    private File archivoActual = null;

    @FXML
    public void initialize() {
        areaTexto.textProperty().addListener((obs, oldVal, newVal) -> actualizarLineas());
    }

    @FXML
    private void handleNuevo() {
        if (!areaTexto.getText().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION,
                    "¿Desea guardar los cambios antes de crear un nuevo archivo?",
                    ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alerta.setTitle("Nuevo archivo");
            alerta.showAndWait().ifPresent(respuesta -> {
                if (respuesta == ButtonType.YES) {
                    handleGuardar();
                } else if (respuesta == ButtonType.CANCEL) {
                    return;
                }
                limpiarEditor();
            });
        } else {
            limpiarEditor();
        }
    }

    @FXML
    private void handleAbrir() {
        FileChooser chooser = crearFileChooser();
        File archivo = chooser.showOpenDialog(getStage());
        if (archivo != null) {
            try {
                String contenido = Files.readString(archivo.toPath(), StandardCharsets.UTF_8);
                areaTexto.setText(contenido);
                archivoActual = archivo;
                lblArchivo.setText("Archivo: " + archivo.getName());
            } catch (IOException e) {
                mostrarError("No se pudo abrir el archivo: " + e.getMessage());
            }
        }
    }

    @FXML
    private void handleGuardar() {
        if (archivoActual == null) {
            handleGuardarComo();
        } else {
            guardarEnArchivo(archivoActual);
        }
    }

    @FXML
    private void handleGuardarComo() {
        FileChooser chooser = crearFileChooser();
        File archivo = chooser.showSaveDialog(getStage());
        if (archivo != null) {
            guardarEnArchivo(archivo);
            archivoActual = archivo;
            lblArchivo.setText("Archivo: " + archivo.getName());
        }
    }

    @FXML
    private void handleCopiar() {
        areaTexto.copy();
    }

    @FXML
    private void handleCortar() {
        areaTexto.cut();
    }

    @FXML
    private void handlePegar() {
        areaTexto.paste();
    }

    @FXML
    private void handleSeleccionarTodo() {
        areaTexto.selectAll();
    }

    @FXML
    private void handleSalir() {
        getStage().close();
    }

    private void limpiarEditor() {
        areaTexto.clear();
        archivoActual = null;
        lblArchivo.setText("Archivo: Sin título");
        actualizarLineas();
    }

    private void guardarEnArchivo(File archivo) {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {
            writer.write(areaTexto.getText());
        } catch (IOException e) {
            mostrarError("No se pudo guardar el archivo: " + e.getMessage());
        }
    }

    private void actualizarLineas() {
        String texto = areaTexto.getText();
        int lineas = texto.isEmpty() ? 0 : texto.split("\n", -1).length;
        int chars = texto.length();
        lblLineas.setText("Líneas: " + lineas + "  |  Caracteres: " + chars);
    }

    private FileChooser crearFileChooser() {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos de texto", "*.txt"),
                new FileChooser.ExtensionFilter("Todos los archivos", "*.*")
        );
        return chooser;
    }

    private Stage getStage() {
        return (Stage) areaTexto.getScene().getWindow();
    }

    private void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
