package com.example.imagenesdataset.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Imagen {

    @JsonProperty("webformatURL")
    private String url;

    @JsonProperty("user")
    private String autor;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    private int numero;

    public Imagen() {}

    public String getUrl() {
        return url;
    }

    public String getAutor() {
        return autor;
    }

}