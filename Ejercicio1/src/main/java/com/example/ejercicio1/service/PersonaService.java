package com.example.ejercicio1.service;

import com.example.ejercicio1.model.Persona;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class PersonaService {

    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Serializa un objeto Persona y lo guarda en el archivo indicado.
     * 
     * @return El contenido JSON generado como cadena (para mostrarlo en UI).
     */
    public String guardarPersona(Persona persona, String rutaArchivo) throws Exception {
        File archivo = new File(rutaArchivo);
        mapper.writeValue(archivo, persona);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(persona);
    }
}
