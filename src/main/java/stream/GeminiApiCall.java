package stream;

import util.FileUtil;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GeminiApiCall {

    public static void main(String[] args) {
        ApiParameters apiParameters = ConfigLoader.readConfiguration();

        String body = FileUtil.readFileFromClasspath("prompt.json")
                .replace("$word", "skjønne");

        makeApiCall(apiParameters.apiUrl(), body);
    }

    public static void makeApiCall(String url, String body) {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofLines())
                  .thenAccept(response ->
                      response.body().forEach(System.out::println)
                  ).join();
        }
    }
}