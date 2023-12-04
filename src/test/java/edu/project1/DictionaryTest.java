package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DictionaryTest {

    Dictionary dictionary = new Dictionary();

    @Test
    @DisplayName("Check the adding words to dictionary")
    void should_addWordToDictionary() {
        String newWord = "myword";

        dictionary.addNewWord(newWord);

        assertThat(dictionary.getDictionary()).asList().contains(newWord);
    }

    @Test
    @DisplayName("Check getting of random words")
    void should_returnRandomWordFromDictionary() {
        String randomWord = dictionary.getRandomWord();

        assertThat(dictionary.getDictionary()).asList().contains(randomWord);

    }

}
