package com.ejemplo.agendacontactos;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AgendaContactosController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtEmail;
    @FXML private TextField txtBuscar;
    @FXML private TableView<Contacto> tablaContactos;
    @FXML private TableColumn<Contacto, String> colNombre;
    @FXML private TableColumn<Contacto, String> colTelefono;
    @FXML private TableColumn<Contacto, String> colEmail;
    @FXML private Label lblTotal;

    private final ObservableList<Contacto> contactos = FXCollections.observableArrayList();
    private FilteredList<Contacto> contactosFiltrados;

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
        colTelefono.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTelefono()));
        colEmail.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));

        contactosFiltrados = new FilteredList<>(contactos, p -> true);
        tablaContactos.setItems(contactosFiltrados);

        txtBuscar.textProperty().addListener((obs, oldVal, newVal) -> {
            String filtro = newVal.toLowerCase();
            contactosFiltrados.setPredicate(c ->
                    filtro.isEmpty()
                    || c.getNombre().toLowerCase().contains(filtro)
                    || c.getTelefono().contains(filtro)
                    || c.getEmail().toLowerCase().contains(filtro)
            );
        });

        tablaContactos.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtNombre.setText(newSel.getNombre());
                txtTelefono.setText(newSel.getTelefono());
                txtEmail.setText(newSel.getEmail());
            }
        });

        actualizarTotal();
    }

    @FXML
    private void handleAgregar() {
        String nombre = txtNombre.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String email = txtEmail.getText().trim();
        if (nombre.isEmpty()) {
            mostrarAlerta("El nombre es obligatorio.");
            return;
        }
        contactos.add(new Contacto(nombre, telefono, email));
        limpiarCampos();
        actualizarTotal();
    }

    @FXML
    private void handleActualizar() {
        Contacto seleccionado = tablaContactos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Seleccione un contacto para actualizar.");
            return;
        }
        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            mostrarAlerta("El nombre es obligatorio.");
            return;
        }
        int indice = contactos.indexOf(seleccionado);
        contactos.set(indice, new Contacto(nombre, txtTelefono.getText().trim(), txtEmail.getText().trim()));
        limpiarCampos();
    }

    @FXML
    private void handleEliminar() {
        Contacto seleccionado = tablaContactos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            contactos.remove(seleccionado);
            limpiarCampos();
            actualizarTotal();
        } else {
            mostrarAlerta("Seleccione un contacto para eliminar.");
        }
    }

    @FXML
    private void handleLimpiar() {
        limpiarCampos();
        tablaContactos.getSelectionModel().clearSelection();
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtTelefono.clear();
        txtEmail.clear();
    }

    private void actualizarTotal() {
        lblTotal.setText("Total de contactos: " + contactos.size());
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Aviso");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public static class Contacto {
        private final String nombre;
        private final String telefono;
        private final String email;

        public Contacto(String nombre, String telefono, String email) {
            this.nombre = nombre;
            this.telefono = telefono;
            this.email = email;
        }

        public String getNombre() { return nombre; }
        public String getTelefono() { return telefono; }
        public String getEmail() { return email; }
    }
}
