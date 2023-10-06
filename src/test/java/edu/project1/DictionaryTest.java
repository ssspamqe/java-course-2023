package edu.project1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
public class DictionaryTest {

    Dictionary dictionary = new Dictionary();

    @Test
    @DisplayName("Check the adding words to dictionary")
    void checkAddingWords(){

        String newWord = "myword";

        dictionary.addNewWord(newWord);

        assertThat(dictionary.getDictionary()).asList().contains(newWord);
    }

    @Test
    @DisplayName("Check the getting of random words")
    void checkRandomWord(){

        String randomWord = dictionary.getRandomWord();

        assertThat(dictionary.getDictionary()).asList().contains(randomWord);

    }

}
