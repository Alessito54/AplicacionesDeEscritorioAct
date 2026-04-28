package com.example.paisesdataset.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Temperatura {
    private double temp;

    @JsonProperty("feels_like")
    private double sensacionTermica;

    @JsonProperty("humidity")
    private int humedad;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getSensacionTermica() {
        return sensacionTermica;
    }

    public void setSensacionTermica(double sensacionTermica) {
        this.sensacionTermica = sensacionTermica;
    }

    public int getHumedad() {
        return humedad;
    }

    public void setHumedad(int humedad) {
        this.humedad = humedad;
    }

    @Override
    public String toString() {
        return "Temperatura\n" +
                "----------------------------\n" +
                "Temperatura: " + String.format("%.2f", temp) + "\n" +
                "Sensación térmica: " + String.format("%.2f", sensacionTermica) + "\n" +
                "Humedad: " + humedad + "%";
    }
}
