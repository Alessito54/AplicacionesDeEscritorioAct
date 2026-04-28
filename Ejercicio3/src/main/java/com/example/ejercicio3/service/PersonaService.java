package com.example.ejercicio3.service;

import com.example.ejercicio3.model.Persona;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class PersonaService {

    /**
     * Construye un JSON manualmente (sin librerias) y lo guarda con FileWriter.
     * 
     * @return La cadena JSON generada.
     */
    public String guardarListaManual(List<Persona> personas, String rutaArchivo) throws Exception {
        String json = "[\n";
        for (int i = 0; i < personas.size(); i++) {
            Persona p = personas.get(i);
            json += "  {\n";
            json += "    \"nombre\": \"" + p.getNombre() + "\",\n";
            json += "    \"edad\": " + p.getEdad() + ",\n";
            json += "    \"ciudad\": \"" + p.getCiudad() + "\"\n";
            json += "  }";
            if (i < personas.size() - 1)
                json += ",";
            json += "\n";
        }
        json += "]";

        FileWriter fw = new FileWriter(new File(rutaArchivo));
        fw.write(json);
        fw.close();
        return json;
    }
}
