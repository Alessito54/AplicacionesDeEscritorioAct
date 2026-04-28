package com.example.paisesdataset.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Clima {
    @JsonProperty("main")
    private String principal;
    @JsonProperty("description")
    private String descripcion;

    @JsonProperty("icon")
    private String icono;

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    @Override
    public String toString() {
        return "Clima detalle\n" +
                "----------------------------\n" +
                "Estado: " + (principal != null ? principal : "N/A") + "\n" +
                "Descripción: " + (descripcion != null ? descripcion : "N/A") + "\n" +
                "Icono: " + (icono != null ? icono : "N/A");
    }
}
