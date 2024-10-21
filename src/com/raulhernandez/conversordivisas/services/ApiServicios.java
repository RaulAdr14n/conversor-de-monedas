package com.raulhernandez.conversordivisas.services;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpClient;
import java.io.IOException;
import java.net.http.HttpTimeoutException;

@SuppressWarnings("unused")
public class ApiServicios {

    public String getApiResponse() {
        String responseString = "";

        try {
            // Crear cliente HTTP
            HttpClient client = HttpClient.newHttpClient();
            
            // Crear solicitud HTTP
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://v6.exchangerate-api.com/v6/54cb9cebba29f0d98d77d3ad/latest/USD"))
                    .build();

            // Enviar solicitud y recibir respuesta
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            
            // Obtener el cuerpo de la respuesta
            responseString = response.body();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
        
        return responseString; // Retornar la respuesta como String
    }
}