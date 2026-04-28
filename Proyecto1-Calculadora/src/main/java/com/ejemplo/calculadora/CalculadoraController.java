package com.ejemplo.calculadora;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculadoraController {

    @FXML
    private TextField pantalla;

    private String operando1 = "";
    private String operador = "";
    private boolean nuevaEntrada = true;

    @FXML
    private void handleDigito(javafx.event.ActionEvent event) {
        Button btn = (Button) event.getSource();
        String digito = btn.getText();
        if (nuevaEntrada) {
            pantalla.setText(digito);
            nuevaEntrada = false;
        } else {
            String actual = pantalla.getText();
            if (actual.equals("0")) {
                pantalla.setText(digito);
            } else {
                pantalla.setText(actual + digito);
            }
        }
    }

    @FXML
    private void handleDecimal() {
        if (nuevaEntrada) {
            pantalla.setText("0.");
            nuevaEntrada = false;
        } else if (!pantalla.getText().contains(".")) {
            pantalla.setText(pantalla.getText() + ".");
        }
    }

    @FXML
    private void handleOperador(javafx.event.ActionEvent event) {
        Button btn = (Button) event.getSource();
        operando1 = pantalla.getText();
        operador = btn.getText();
        nuevaEntrada = true;
    }

    @FXML
    private void handleIgual() {
        if (operador.isEmpty() || operando1.isEmpty()) return;
        double a = Double.parseDouble(operando1);
        double b = Double.parseDouble(pantalla.getText());
        double resultado = switch (operador) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "×" -> a * b;
            case "÷" -> b != 0 ? a / b : Double.NaN;
            default -> b;
        };
        if (Double.isNaN(resultado)) {
            pantalla.setText("Error");
        } else if (resultado == Math.floor(resultado) && !Double.isInfinite(resultado)) {
            pantalla.setText(String.valueOf((long) resultado));
        } else {
            pantalla.setText(String.valueOf(resultado));
        }
        operando1 = "";
        operador = "";
        nuevaEntrada = true;
    }

    @FXML
    private void handleLimpiar() {
        pantalla.setText("0");
        operando1 = "";
        operador = "";
        nuevaEntrada = true;
    }

    @FXML
    private void handleBorrar() {
        String actual = pantalla.getText();
        if (actual.length() > 1) {
            pantalla.setText(actual.substring(0, actual.length() - 1));
        } else {
            pantalla.setText("0");
            nuevaEntrada = true;
        }
    }

    @FXML
    private void handlePorcentaje() {
        try {
            double val = Double.parseDouble(pantalla.getText()) / 100;
            pantalla.setText(String.valueOf(val));
            nuevaEntrada = true;
        } catch (NumberFormatException ignored) {
        }
    }

    @FXML
    private void handleNegarPositivo() {
        try {
            double val = Double.parseDouble(pantalla.getText()) * -1;
            if (val == Math.floor(val)) {
                pantalla.setText(String.valueOf((long) val));
            } else {
                pantalla.setText(String.valueOf(val));
            }
        } catch (NumberFormatException ignored) {
        }
    }
}
