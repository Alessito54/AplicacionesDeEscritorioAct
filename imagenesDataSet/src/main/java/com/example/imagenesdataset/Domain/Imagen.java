package com.example.imagenesdataset.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Imagen {
    @JsonProperty("download_url")
    private  String url;
    @JsonProperty("author")
    private  String autor;
    public Imagen(){}
    public Imagen(String url, String autor)
    {
        this.autor = autor;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getAutor() {
        return autor;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
