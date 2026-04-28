package com.example.crud_mongo.controller;

import com.example.crud_mongo.dao.UsuarioDAO;
import com.example.crud_mongo.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.List;

public class UsuarioController {

    @FXML
    private TableView<Usuario> tableUsuarios;
    @FXML
    private TableColumn<Usuario, String> colNombre;
    @FXML
    private TableColumn<Usuario, Integer> colEdad;

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtEdad;
    @FXML
    private VBox formContainer;

    private UsuarioDAO usuarioDAO;
    private ObservableList<Usuario> usuariosList;
    private Usuario usuarioSeleccionado;

    @FXML
    public void initialize() {
        usuarioDAO = new UsuarioDAO();
        usuariosList = FXCollections.observableArrayList();

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));

        tableUsuarios.setItems(usuariosList);

        // Listener for table selection
        tableUsuarios.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> seleccionarUsuario(newValue));

        cargarUsuarios();
    }

    private void cargarUsuarios() {
        usuariosList.clear();
        List<Usuario> list = usuarioDAO.obtenerUsuarios();
        usuariosList.addAll(list);
    }

    private void seleccionarUsuario(Usuario usuario) {
        if (usuario != null) {
            usuarioSeleccionado = usuario;
            txtNombre.setText(usuario.getNombre());
            txtEdad.setText(String.valueOf(usuario.getEdad()));
            formContainer.getStyleClass().add("form-active");
        } else {
            usuarioSeleccionado = null;
            limpiarCampos();
            formContainer.getStyleClass().remove("form-active");
        }
    }

    @FXML
    void agregarUsuario(ActionEvent event) {
        try {
            String nombre = txtNombre.getText();
            int edad = Integer.parseInt(txtEdad.getText());

            Usuario nuevo = new Usuario(nombre, edad);
            usuarioDAO.crearUsuario(nuevo);
            cargarUsuarios();
            limpiarCampos();
            mostrarAlerta("Éxito", "Usuario agregado correctamente", Alert.AlertType.INFORMATION);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "La edad debe ser un número válido.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void actualizarUsuario(ActionEvent event) {
        if (usuarioSeleccionado != null) {
            try {
                usuarioSeleccionado.setNombre(txtNombre.getText());
                usuarioSeleccionado.setEdad(Integer.parseInt(txtEdad.getText()));

                usuarioDAO.actualizarUsuario(usuarioSeleccionado);
                cargarUsuarios();
                limpiarCampos();
                tableUsuarios.getSelectionModel().clearSelection();
                mostrarAlerta("Éxito", "Usuario actualizado", Alert.AlertType.INFORMATION);
            } catch (NumberFormatException e) {
                mostrarAlerta("Error", "La edad debe ser un número válido.", Alert.AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Advertencia", "Seleccione un usuario para actualizar", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void eliminarUsuario(ActionEvent event) {
        if (usuarioSeleccionado != null) {
            usuarioDAO.eliminarUsuario(usuarioSeleccionado.getId());
            cargarUsuarios();
            limpiarCampos();
            tableUsuarios.getSelectionModel().clearSelection();
            mostrarAlerta("Éxito", "Usuario eliminado", Alert.AlertType.INFORMATION);
        } else {
            mostrarAlerta("Advertencia", "Seleccione un usuario para eliminar", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void limpiarFormulario(ActionEvent event) {
        tableUsuarios.getSelectionModel().clearSelection();
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtEdad.clear();
        usuarioSeleccionado = null;
    }

    private void mostrarAlerta(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
