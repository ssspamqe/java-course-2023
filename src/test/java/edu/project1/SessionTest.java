package edu.project1;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    }

    @Test
    @DisplayName("Session:tryGuess should throw an exception if passed played character")
    void tryGuess_should_throwAnException_ifPassedPlayedCharacter() {
        char character = 'a';

        session.tryGuess(character);

        assertThrows(Exception.class, () -> session.tryGuess(character));
    }

    @Test
    @DisplayName("Session:tryGuess should throw an exception if passed not a letter")
    void tryGuess_should_throwAnException_ifPassedNotLetter() {
        char character = '6';

        assertThrows(Exception.class, () -> session.tryGuess(character));
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

        assertTrue(session.won());
    }

    @Test
    @DisplayName("Check lose situation")
    void check_Win() {
        String word = session.getWord();
        int healthPoints = session.getMaxHealthPoints();

        for (int i = 0; i < 26; i++) {
            char letter = (char) ('a' + i);
            if (word.indexOf(letter) < 0) {
                session.tryGuess(letter);
                healthPoints--;
            }
            if (healthPoints == 0) {
                break;
            }
        }

        assertTrue(session.lost());
    }

}
