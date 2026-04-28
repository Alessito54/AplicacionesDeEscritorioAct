package com.example.paisesdataset.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pais {

    private Name name;
    private List<String> capital;
    private String region;
    private long population;
    private List<String> timezones;
    private List<Double> latlng;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public List<String> getCapital() {
        return capital;
    }

    public void setCapital(List<String> capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public List<String> getTimezones() {
        return timezones;
    }

    public void setTimezones(List<String> timezones) {
        this.timezones = timezones;
    }

    public List<Double> getLatlng() {
        return latlng;
    }

    public void setLatlng(List<Double> latlng) {
        this.latlng = latlng;
    }

    @Override
    public String toString() {
        return "País\n" +
                "----------------------------\n" +
                "Nombre: " + (name != null ? name.getCommon() : "N/A") + "\n" +
                "Capital: " + (capital != null && !capital.isEmpty() ? capital.get(0) : "N/A") + "\n" +
                "Región: " + (region != null ? region : "N/A") + "\n" +
                "Población: " + String.format("%,d", population) + "\n" +
                "Zona horaria: " + (timezones != null && !timezones.isEmpty() ? timezones.get(0) : "N/A") + "\n" +
                "Latitud: " + (latlng != null && latlng.size() > 0 ? String.format("%.2f", latlng.get(0)) : "N/A") + "\n" +
                "Longitud: " + (latlng != null && latlng.size() > 1 ? String.format("%.2f", latlng.get(1)) : "N/A");
    }
}
