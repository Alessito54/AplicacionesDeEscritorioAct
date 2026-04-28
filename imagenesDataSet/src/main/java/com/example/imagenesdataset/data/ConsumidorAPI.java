package com.example.imagenesdataset.data;

import com.example.imagenesdataset.Domain.Imagen;
import com.example.imagenesdataset.Domain.PixabayResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ConsumidorAPI {
    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    public static String cargarDatos() {
        try {

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://pixabay.com/api/?key=" + dotenv.get("PIXABAY_KEY")
                            + "&q=video+games&image_type=photo&per_page=200"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Imagen> cargarDatosList() {
        try {
            String json = cargarDatos();

            ObjectMapper mapper = new ObjectMapper();

            PixabayResponse response = mapper.readValue(json, PixabayResponse.class);

            return response.getHits();

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
