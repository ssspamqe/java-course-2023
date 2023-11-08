package edu.hw6;

import edu.hw6.Task5.HackerNews;
import java.io.IOException;
import java.net.URISyntaxException;

public class Test {

    public static void main(String[] params) throws IOException, URISyntaxException, InterruptedException {
        HackerNews hackerNews = new HackerNews();
        hackerNews.getHackerNewsTopStories();
    }
}
