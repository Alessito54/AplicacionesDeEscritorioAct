package com.example.paisesdataset.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClimaRespuesta {
    @JsonProperty("weather")
    private List<Clima> clima;
    @JsonProperty("main")
    private Temperatura temperatura;

    public List<Clima> getClima() {
        return clima;
    }

    public void setClima(List<Clima> clima) {
        this.clima = clima;
    }

    public Temperatura getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Temperatura temperatura) {
        this.temperatura = temperatura;
    }

    @Override
    public String toString() {
        return "ClimaRespuesta{" +
                "clima=" + clima +
                ", temperatura=" + temperatura +
                '}';
    }
}
