package com.example.imagenesdataset.Domain;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PixabayResponse {

    private List<Imagen> hits;

    public List<Imagen> getHits() {
        return hits;
    }

    public void setHits(List<Imagen> hits) {
        this.hits = hits;
    }
}