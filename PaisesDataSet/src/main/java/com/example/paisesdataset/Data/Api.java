package com.example.paisesdataset.Data;

import com.example.paisesdataset.Domain.ClimaRespuesta;
import com.example.paisesdataset.Domain.Pais;
import com.example.paisesdataset.Domain.ZonaTiempo;
import com.example.paisesdataset.HelloApplication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class Api {
    public static String cosumirPais(String pais){
        pais = pais.replace(" ", "%20");
        try {
            URL url = new URL("https://restcountries.com/v3.1/name/"
                    + pais
                    + "?fields=name,capital,region,population,timezones,latlng");
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            conexion.setRequestProperty("User-Agent", "Mozilla/5.0");

            BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(conexion.getInputStream()));
            String linea;
            StringBuilder response = new StringBuilder();
            while ((linea = reader.readLine()) != null) {
                response.append(linea);
            }
            return response.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static String cosumirhora(double lat, double lng){

        try {
            URL url = new URL(
                    "http://api.timezonedb.com/v2.1/get-time-zone?key=QRO1OOTKZP38&by=position&format=json"
                            + "&lat=" + lat
                            + "&lng=" + lng
            );
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            conexion.setRequestProperty("User-Agent", "Mozilla/5.0");
            conexion.setRequestProperty("Accept", "application/json");

            BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(conexion.getInputStream()));
            String linea;
            StringBuilder response = new StringBuilder();
            while ((linea = reader.readLine()) != null) {
                response.append(linea);
            }
            return response.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static String consumirClima(double lat, double lng) {
        try {
            URL url = new URL(
                    "https://api.openweathermap.org/data/2.5/weather"
                            + "?lat=" + lat
                            + "&lon=" + lng
                            + "&appid=44e6aade7ebc7395254e995689660dff"
                            + "&units=metric"
                            + "&lang=es"
            );

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setRequestProperty("Accept", "application/json");

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            BufferedReader bufferedReader;

            if (status >= 200 && status < 300) {
                bufferedReader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream())
                );
            } else {
                bufferedReader = new BufferedReader(
                        new InputStreamReader(connection.getErrorStream())
                );
            }

            String linea;
            StringBuilder stringBuilder = new StringBuilder();

            while ((linea = bufferedReader.readLine()) != null) {
                stringBuilder.append(linea);
            }

            bufferedReader.close();

            return stringBuilder.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        String json = consumirClima(19.43, -99.13);

        ClimaRespuesta data = mapper.readValue(json, ClimaRespuesta.class);

        String icono = data.getClima().get(0).getIcono();
        double temp = data.getTemperatura().getTemp();
        int humedad = data.getTemperatura().getHumedad();

        System.out.println(data.getClima());
        System.out.println(data.getTemperatura());

    }
    public static ArrayList<Pais> obtenerListaPaises() {

        try {
            URL url = new URL("https://restcountries.com/v3.1/all?fields=name");

            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            conexion.setRequestProperty("User-Agent", "Mozilla/5.0");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conexion.getInputStream())
            );

            String linea;
            StringBuilder response = new StringBuilder();

            while ((linea = reader.readLine()) != null) {
                response.append(linea);
            }

            reader.close();

            ObjectMapper mapper = new ObjectMapper();

            Pais[] paisesArray = mapper.readValue(response.toString(), Pais[].class);

            return new ArrayList<>(Arrays.asList(paisesArray));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
