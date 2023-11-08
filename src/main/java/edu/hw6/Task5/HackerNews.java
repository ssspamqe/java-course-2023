package edu.hw6.Task5;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.regex.Pattern;
import static java.net.http.HttpClient.newHttpClient;

public class HackerNews {
    public long[] getHackerNewsTopStories() {

        HttpResponse<String> response;

        try {
            var request = HttpRequest.newBuilder()
                .uri(new URI("https://hacker-news.firebaseio.com/v0/topstories.json"))
                .GET()
                .timeout(Duration.of(5, ChronoUnit.SECONDS))
                .build();

            response = newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return parseTopStories(response.body());
    }

    private long[] parseTopStories(String response) {
        var stringIds = response.substring(1, response.length() - 1).split(",");

        return Arrays.stream(stringIds).mapToLong(Long::parseLong).toArray();
    }

    public String getNewsName(long id) throws URISyntaxException, IOException, InterruptedException {

        String responseBody;

        try {
            var request = HttpRequest.newBuilder()
                .uri(new URI("https://hacker-news.firebaseio.com/v0/item/" + id + ".json"))
                .GET()
                .timeout(Duration.of(5, ChronoUnit.SECONDS))
                .build();

            responseBody = newHttpClient().send(request, HttpResponse.BodyHandlers.ofString()).body();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        var pattern = Pattern.compile(".*\"title\":\"(.*)\",\"type\"");

        return pattern.matcher(responseBody).group(1);
    }

}
