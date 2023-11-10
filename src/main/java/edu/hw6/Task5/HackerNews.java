package edu.hw6.Task5;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.regex.Pattern;
import static java.net.http.HttpClient.newHttpClient;

public class HackerNews {

    private static final int MAX_TIMEOUT = 5;

    public long[] getHackerNewsTopStories() {

        String responseBody = getResponseBody("topstories.json");

        return parseTopStories(responseBody);
    }

    public String getNewsName(long id) {

        String responseBody = getResponseBody("item/" + id + ".json");

        var pattern = Pattern.compile("\"title\":\"([^\"]*)\"");
        var matcher = pattern.matcher(responseBody);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }

    private String getResponseBody(String req) {
        try {
            var request = HttpRequest.newBuilder()
                .uri(new URI("https://hacker-news.firebaseio.com/v0/" + req))
                .GET()
                .timeout(Duration.of(MAX_TIMEOUT, ChronoUnit.SECONDS))
                .build();

            return newHttpClient().send(request, HttpResponse.BodyHandlers.ofString()).body();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private long[] parseTopStories(String response) {
        var stringIds = response.substring(1, response.length() - 1).split(",");

        return Arrays.stream(stringIds).mapToLong(Long::parseLong).toArray();
    }

}
