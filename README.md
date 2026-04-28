# AplicacionesDeEscritorioAct

Colección de **6 proyectos JavaFX** desarrollados para la materia de Aplicaciones de Escritorio.  
Cada proyecto es independiente, con estructura Maven estándar y puede ejecutarse directamente con `mvn javafx:run`.

## Requisitos

- Java 17+
- Maven 3.6+

---

## Proyectos

### Proyecto 1 — Calculadora

Calculadora con diseño oscuro (tema Catppuccin Mocha).  
**Funciones:** suma, resta, multiplicación, división, porcentaje, negación de signo, borrado carácter a carácter y limpieza completa.

```bash
cd Proyecto1-Calculadora
mvn javafx:run
```

---

### Proyecto 2 — Lista de Tareas

Gestor de tareas con lista dinámica.  
**Funciones:** agregar tareas, marcarlas como completadas (✔), eliminarlas individualmente y limpiar toda la lista.

```bash
cd Proyecto2-ListaDeTareas
mvn javafx:run
```

---

### Proyecto 3 — Gestión de Estudiantes

Registro de calificaciones por estudiante y materia, con cálculo automático del promedio.  
**Funciones:** agregar / eliminar registros, validación de rango (0–10), TableView con columnas Nombre / Materia / Calificación.

```bash
cd Proyecto3-GestionEstudiantes
mvn javafx:run
```

---

### Proyecto 4 — Editor de Texto

Editor de texto simple con soporte para archivos `.txt`.  
**Funciones:** nuevo, abrir, guardar, guardar como, copiar / cortar / pegar, seleccionar todo, contador de líneas y caracteres en tiempo real.

```bash
cd Proyecto4-EditorDeTexto
mvn javafx:run
```

---

### Proyecto 5 — Agenda de Contactos

Directorio de contactos con búsqueda en tiempo real.  
**Funciones:** agregar, actualizar y eliminar contactos (nombre, teléfono, correo), filtrado instantáneo al escribir en el campo de búsqueda.

```bash
cd Proyecto5-AgendaContactos
mvn javafx:run
```

---

### Proyecto 6 — Quiz Trivia

Quiz de opción múltiple con 10 preguntas sobre Java y JavaFX.  
**Funciones:** selección de respuesta, retroalimentación visual (verde = correcto, rojo = incorrecto), puntaje acumulado, calificación final y opción de reinicio.

```bash
cd Proyecto6-QuizTrivia
mvn javafx:run
```

---

## Estructura de cada proyecto

```
ProyectoN-Nombre/
├── pom.xml
└── src/
    └── main/
        ├── java/
        │   ├── module-info.java
        │   └── com/ejemplo/<paquete>/
        │       ├── App.java            # Punto de entrada JavaFX
        │       └── *Controller.java    # Lógica de la interfaz
        └── resources/
            └── com/ejemplo/<paquete>/
                └── main.fxml           # Definición de la interfaz (FXML)
```
