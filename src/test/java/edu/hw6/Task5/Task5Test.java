package edu.hw6.Task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {

    HackerNews hackerNews = new HackerNews();

    @Test
    @DisplayName("getNewsName() should return a name of news by given id")
    void getNewsName_should_returnNewsName_byGivenId() {
        var correctNewsName = "JDK 21 Release Notes";
        var newsId = 37570037;

        var returnedNewsName = hackerNews.getNewsName(newsId);

        assertThat(returnedNewsName).isEqualTo(correctNewsName);
    }

    @Test
    @DisplayName("getNewsName() should return a name of news by given id")
    void getNewsName_should_returnNull_serverDidntResponseNews() {
        var newsId = -1;

        var returnedNewsName = hackerNews.getNewsName(newsId);

        assertThat(returnedNewsName).isEqualTo(null);
    }

}
