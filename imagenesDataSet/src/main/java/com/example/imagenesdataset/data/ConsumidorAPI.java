package com.example.imagenesdataset.data;

import com.example.imagenesdataset.Domain.Imagen;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ConsumidorAPI {
   public static String cargarDatos(){
       try {



           HttpClient client = HttpClient.newHttpClient();

           HttpRequest request = HttpRequest.newBuilder()
                   .uri(URI.create("https://picsum.photos/v2/list?page=1"))
                   .GET()
                   .build();


           HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


           return  response.body();


       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
   }
   public static List<Imagen> cargarDatosList(){
       try {

           String json = cargarDatos();
           if (json == null) {
               return new ArrayList<>();
           }
           ObjectMapper mapper = new ObjectMapper();
           return mapper.readValue(json, new TypeReference<List<Imagen>>() {});
       }catch (Exception e){
           e.printStackTrace();
           return null;
       }

   }

}
