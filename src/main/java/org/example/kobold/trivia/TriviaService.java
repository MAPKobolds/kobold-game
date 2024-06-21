package org.example.kobold.trivia;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TriviaService {

    public static String getTrivia() {
        String responseBody = null;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://opentdb.com/api.php?amount=10&category=11&difficulty=easy"))
                .build();

        CompletableFuture<HttpResponse<String>> responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());


        try {
            HttpResponse<String> response = responseFuture.get();
            int statusCode = response.statusCode();
            responseBody = response.body();

            switch (statusCode) {
                case 200:
                    System.out.println("Success");
                    break;
                case 429:
                    throw new RuntimeException("Can't reach the server. Try again later.");
                case 500:
                    throw new RuntimeException("Internal Server Error");
                default:
                    throw new RuntimeException("Unknown error occurred.");
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return responseBody;
    }
}
