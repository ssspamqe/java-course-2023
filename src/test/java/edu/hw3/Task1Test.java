package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {

    Task1 task = new Task1();

    @Test
    @DisplayName("Method should \"inverse\" latin letters according to alphabet holding their case")
    void check_latinLetters(){
        String originalString = "abcML";

        String encodedString = task.atbash(originalString);

        assertThat(encodedString).isEqualTo("zyxNO");
    }

    @Test
    @DisplayName("Method should not change non latin letters")
    void check_nonLatinLetters(){
        String originalString = "тестAb";

        String encodedString = task.atbash(originalString);

        assertThat(encodedString).isEqualTo("тестZy");
    }

}
