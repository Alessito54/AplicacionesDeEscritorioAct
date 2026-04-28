package com.example.paisesdataset.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ZonaTiempo
{
    private String formatted;
    private String regionName;

    public String getFormatted() {
        return formatted;
    }

    public void setFormatted(String formatted) {
        this.formatted = formatted;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public String toString() {
        return "Zona Horaria\n" +
                "----------------------------\n" +
                "Región: " + (regionName != null ? regionName : "N/A") + "\n" +
                "Hora: " + (formatted != null ? formatted : "N/A");
    }
}
