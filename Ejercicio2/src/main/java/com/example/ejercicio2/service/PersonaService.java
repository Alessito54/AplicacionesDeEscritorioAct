package com.example.ejercicio2.service;

import com.example.ejercicio2.model.Persona;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class PersonaService {

    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Serializa la lista y la guarda en el archivo indicado.
     * 
     * @return El JSON generado como cadena.
     */
    public String guardarLista(List<Persona> personas, String rutaArchivo) throws Exception {
        File archivo = new File(rutaArchivo);
        mapper.writeValue(archivo, personas);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(personas);
    }

    /**
     * Lee el archivo JSON y devuelve la lista de personas deserializada.
     */
    public List<Persona> leerLista(String rutaArchivo) throws Exception {
        File archivo = new File(rutaArchivo);
        return Arrays.asList(mapper.readValue(archivo, Persona[].class));
    }
}
