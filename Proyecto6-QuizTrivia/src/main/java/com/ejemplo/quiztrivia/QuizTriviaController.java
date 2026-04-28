package com.ejemplo.quiztrivia;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class QuizTriviaController {

    @FXML private Label lblPregunta;
    @FXML private Label lblNumero;
    @FXML private Label lblPuntaje;
    @FXML private Button btnA;
    @FXML private Button btnB;
    @FXML private Button btnC;
    @FXML private Button btnD;
    @FXML private Button btnSiguiente;
    @FXML private Label lblResultado;

    private final List<Pregunta> preguntas = List.of(
        new Pregunta("¿Cuál es el lenguaje de programación de JavaFX?",
            "Python", "Java", "C++", "JavaScript", 'B'),
        new Pregunta("¿Qué componente se usa para mostrar listas en JavaFX?",
            "TableView", "GridPane", "ListView", "HBox", 'C'),
        new Pregunta("¿Cuál es el archivo de diseño de interfaz en JavaFX?",
            ".xml", ".html", ".json", ".fxml", 'D'),
        new Pregunta("¿Qué clase es la base de una aplicación JavaFX?",
            "Application", "Stage", "Scene", "Node", 'A'),
        new Pregunta("¿Cuál componente muestra datos en filas y columnas?",
            "ListView", "TableView", "GridPane", "VBox", 'B'),
        new Pregunta("¿Qué método inicia la aplicación JavaFX?",
            "run()", "init()", "start()", "launch()", 'C'),
        new Pregunta("¿Cuál es la versión LTS más reciente de Java?",
            "11", "17", "8", "14", 'B'),
        new Pregunta("¿Qué gestor de dependencias es común en proyectos Java?",
            "npm", "pip", "Maven", "Composer", 'C'),
        new Pregunta("¿Qué es JavaFX?",
            "Un servidor web", "Una librería para interfaces gráficas", "Un sistema operativo", "Un motor de base de datos", 'B'),
        new Pregunta("¿Cuál es el controlador del patrón MVC en JavaFX?",
            "El FXML", "El Stage", "La clase Controller", "El módulo", 'C')
    );

    private int preguntaActual = 0;
    private int puntaje = 0;
    private boolean respondida = false;

    @FXML
    public void initialize() {
        mostrarPregunta();
    }

    private void mostrarPregunta() {
        if (preguntaActual >= preguntas.size()) {
            mostrarResultadoFinal();
            return;
        }
        respondida = false;
        Pregunta p = preguntas.get(preguntaActual);
        lblNumero.setText("Pregunta " + (preguntaActual + 1) + " de " + preguntas.size());
        lblPregunta.setText(p.getTexto());
        btnA.setText("A) " + p.getOpcionA());
        btnB.setText("B) " + p.getOpcionB());
        btnC.setText("C) " + p.getOpcionC());
        btnD.setText("D) " + p.getOpcionD());
        for (Button btn : List.of(btnA, btnB, btnC, btnD)) {
            btn.setDisable(false);
            btn.setStyle("");
        }
        lblResultado.setText("");
        btnSiguiente.setDisable(true);
    }

    @FXML
    private void handleRespuestaA() { verificarRespuesta('A', btnA); }
    @FXML
    private void handleRespuestaB() { verificarRespuesta('B', btnB); }
    @FXML
    private void handleRespuestaC() { verificarRespuesta('C', btnC); }
    @FXML
    private void handleRespuestaD() { verificarRespuesta('D', btnD); }

    private void verificarRespuesta(char respuesta, Button btnPresionado) {
        if (respondida) return;
        respondida = true;
        Pregunta p = preguntas.get(preguntaActual);
        for (Button btn : List.of(btnA, btnB, btnC, btnD)) {
            btn.setDisable(true);
        }
        Button btnCorrect = switch (p.getRespuestaCorrecta()) {
            case 'A' -> btnA;
            case 'B' -> btnB;
            case 'C' -> btnC;
            default -> btnD;
        };
        btnCorrect.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        if (respuesta == p.getRespuestaCorrecta()) {
            puntaje++;
            lblResultado.setText("✔ ¡Correcto!");
            lblResultado.setStyle("-fx-text-fill: #4CAF50;");
        } else {
            btnPresionado.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
            lblResultado.setText("✘ Incorrecto. La respuesta correcta es: " + p.getRespuestaCorrecta());
            lblResultado.setStyle("-fx-text-fill: #f44336;");
        }
        lblPuntaje.setText("Puntaje: " + puntaje);
        btnSiguiente.setDisable(false);
    }

    @FXML
    private void handleSiguiente() {
        preguntaActual++;
        mostrarPregunta();
    }

    @FXML
    private void handleReiniciar() {
        preguntaActual = 0;
        puntaje = 0;
        lblPuntaje.setText("Puntaje: 0");
        mostrarPregunta();
        btnSiguiente.setDisable(true);
    }

    private void mostrarResultadoFinal() {
        lblPregunta.setText("¡Quiz terminado!");
        lblNumero.setText("Resultado final");
        btnA.setVisible(false);
        btnB.setVisible(false);
        btnC.setVisible(false);
        btnD.setVisible(false);
        btnSiguiente.setDisable(true);
        String calificacion = puntaje >= 9 ? "Excelente" : puntaje >= 7 ? "Bien" : puntaje >= 5 ? "Regular" : "Necesitas practicar";
        lblResultado.setText("Obtuviste " + puntaje + " de " + preguntas.size() + " respuestas correctas.\n" + calificacion + "!");
        lblResultado.setStyle("-fx-text-fill: #2196F3;");
    }

    public static class Pregunta {
        private final String texto;
        private final String opcionA;
        private final String opcionB;
        private final String opcionC;
        private final String opcionD;
        private final char respuestaCorrecta;

        public Pregunta(String texto, String a, String b, String c, String d, char correcta) {
            this.texto = texto;
            this.opcionA = a;
            this.opcionB = b;
            this.opcionC = c;
            this.opcionD = d;
            this.respuestaCorrecta = correcta;
        }

        public String getTexto() { return texto; }
        public String getOpcionA() { return opcionA; }
        public String getOpcionB() { return opcionB; }
        public String getOpcionC() { return opcionC; }
        public String getOpcionD() { return opcionD; }
        public char getRespuestaCorrecta() { return respuestaCorrecta; }
    }
}
