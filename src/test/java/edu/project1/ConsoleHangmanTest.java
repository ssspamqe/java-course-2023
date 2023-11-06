package edu.project1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ConsoleHangmanTest {

    ConsoleHangman game;

    @BeforeEach
    void initializeObject() {
        game = new ConsoleHangman();
    }

    static Arguments[] incorrectLines() {
        return new Arguments[] {
            Arguments.of("123"),
            Arguments.of("exxit"),
            Arguments.of("1"),
            Arguments.of("A")
        };
    }

    @ParameterizedTest
    @MethodSource("incorrectLines")
    @DisplayName("Check isIncorrectInput(String line)")
    void check_isIncorrectInput(String line) {
        assertThat(game.isIncorrectInput(line)).isTrue();
    }
}
