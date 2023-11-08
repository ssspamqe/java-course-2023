package edu.hw6.Task5;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import static java.net.http.HttpClient.newHttpClient;

public class HackerNews {
    public long[] getHackerNewsTopStories() throws URISyntaxException, IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
            .uri(new URI("https://hacker-news.firebaseio.com/v0/topstories.json"))
            .GET()
            .timeout(Duration.of(5, ChronoUnit.SECONDS))
            .build();

        var response = newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return parseTopStories(response.body());
    }

    private long[] parseTopStories(String response){
        var stringIds = response.substring(1, response.length()-1).split(",");

        return Arrays.stream(stringIds).mapToLong(Long::parseLong).toArray();
    }


    public String getNewsName(long id) throws URISyntaxException, IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
            .uri(new URI("https://hacker-news.firebaseio.com/v0/item/"+id+".json"))
            .GET()
            .timeout(Duration.of(5, ChronoUnit.SECONDS))
            .build();

        var responseBody = newHttpClient().send(request, HttpResponse.BodyHandlers.ofString()).body();

        var pattern = Pattern.compile(".*\"title\":\"(.*)\",\"type\"");

        return pattern.matcher(responseBody).group(1);
    }


}
