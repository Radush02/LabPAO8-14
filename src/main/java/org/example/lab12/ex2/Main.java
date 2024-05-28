package org.example.lab12.ex2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

public class Main {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/posts/";

    public static void main(String[] args) throws IOException, InterruptedException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Introduceti comanda (GET/POST)\nPentru post, modifica posts.json:");
        String name = reader.readLine();
        switch (name.toUpperCase()) {
            case "GET" -> getPost(reader);
            case "POST" -> postPost();
            default -> System.out.println("GET/POST");
        }
    }

    private static void getPost(BufferedReader reader) throws IOException, InterruptedException {
        System.out.println("Introdu id-ul postarii:");
        String id = reader.readLine();
        if (id.matches("^(100|[1-9][0-9]?)$")) { // Stim ca id-ul e intre 1 si 100
            HttpRequest request =
                    HttpRequest.newBuilder().uri(URI.create(BASE_URL + id)).GET().build();
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } else {
            System.out.println("Id-ul trebuie sa fie intre 1 si 100");
        }
    }

    private static void postPost() throws IOException {
        String filePath = "src/main/java/org/example/lab12/ex2/posts.json";
        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(URI.create(BASE_URL))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofFile(Path.of(filePath)))
                        .build();
        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
