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

    @BeforeEach
    public void initializeSession() {
        session = new Session();
    }

    @Test
    @DisplayName("Check guess attempt")
    void checkGuess() {

        String word = session.getWord();

        char letter = word.charAt(0);

        int occurrences = word.length() - word.replace(word.substring(0, 1), "").length();

        assertThat(session.tryGuess(letter)).isEqualTo(occurrences);
        assertThat(session.tryGuess('5')).isEqualTo(0);
    }

    @Test
    @DisplayName("Check win situation")
    void checkWin() {

        String word = session.getWord();

        Set<Character> letters = new HashSet<Character>();

        word.chars().forEach(i -> letters.add((char) i));

        for(var i : letters){
            assertThat(session.tryGuess(i)).isGreaterThan(0);
        }

        assertThat(session.won()).isEqualTo(true);

    }

    @Test
    @DisplayName("Check lose situation")
    void checkLose() {

        String word = session.getWord();

        Set<Character> letters = new HashSet<Character>();

        word.chars().forEach(i -> letters.add((char) i));

        int mistakes = 0;

        for(int i = 0;i<26;i++)
        {
            char letter = (char)((int)'a'+i);

            if(!letters.contains(letter)){
                session.tryGuess(letter);
                mistakes++;
            }

        }


        assertThat(session.getMistakes()).isGreaterThan(session.getMaxMistakes());
        assertThat(session.getMistakes()).isEqualTo(mistakes);
    }

}
