package com.alura.Libroslitera.Servicio;

import com.alura.Libroslitera.Modelo.Autor;
import com.alura.Libroslitera.Modelo.Libro;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@Service
public class GutendexService {
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Optional<Libro> buscarLibroPorTitulo(String titulo) {
        try {
            String url = "https://gutendex.com/books/?search=" + titulo;
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonNode rootNode = objectMapper.readTree(response.body());
            JsonNode bookNode = rootNode.path("results").get(0);

            if (bookNode != null) {
                Libro libro = new Libro();
                libro.setTitulo(bookNode.get("title").asText());
                libro.setIdioma(bookNode.path("languages").get(0).asText());
                libro.setDescargas(bookNode.get("download_count").asInt());

                JsonNode authorNode = bookNode.path("authors").get(0);
                Autor autor = new Autor();
                autor.setNombre(authorNode.get("name").asText());
                autor.setAñoNacimiento(authorNode.get("birth_year").asInt());
                autor.setAñoFallecimiento(authorNode.get("death_year").asInt());

                libro.setAutor(autor);
                return Optional.of(libro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
