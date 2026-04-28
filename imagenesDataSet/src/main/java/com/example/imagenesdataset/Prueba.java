package com.example.imagenesdataset;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Prueba {
    public static void main(String[] args) {
        try {



                HttpClient client = HttpClient.newHttpClient();


                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://picsum.photos/v2/list?page=1"))
                        .GET()
                        .build();


                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


                System.out.println(response.body());



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}