package edu.project1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import java.util.HashSet;
import java.util.Set;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SessionTest {

    Session session;
    ConsoleHangman game;

    @BeforeEach
    public void initializeObjects() {
        session = new Session();
    }

    @Test
    @DisplayName("Check guess attempt")
    void check_guess() {

        String word = session.getWord();

        char letter = word.charAt(0);

        int occurrences = word.length() - word.replace(word.substring(0, 1), "").length();

        assertThat(session.tryGuess(letter)).isEqualTo(occurrences);
        assertThat(session.tryGuess('5')).isEqualTo(0);
    }

    @Test
    @DisplayName("Check win situation")
    void check_win() {

        String word = session.getWord();

        Set<Character> letters = new HashSet<Character>();

        word.chars().forEach(i -> letters.add((char) i));

        for (var i : letters) {
            assertThat(session.tryGuess(i)).isGreaterThan(0);
        }

        assertThat(session.won()).isEqualTo(true);

    }

    @Test
    @DisplayName("Check lose situation")
    void check_lose() {
        int mistakes = 0;
        for (int i = 0; i < 26; i++) {
            char letter = (char) ((int) 'a' + i);
            if (session.tryGuess(letter) == 0) {
                mistakes++;
            }
            if (mistakes == session.getMaxMistakes()) {
                break;
            }
        }

        assertThat(session.getMistakes()).isEqualTo(mistakes);
        assertThat(session.getMaxMistakes()).isEqualTo(mistakes);
    }

}
