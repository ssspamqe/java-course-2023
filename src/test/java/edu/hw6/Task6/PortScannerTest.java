package edu.hw6.Task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.regex.Pattern;
import static org.assertj.core.api.Assertions.assertThat;

public class PortScannerTest {

    Pattern portLine = PortScanner.PORT_LINE;

    @ParameterizedTest
    @ValueSource(strings = {
        "1001 - my service",
        "2513 - service1",
        "0 - unknown serviiiice",
        "12 - -asda"
    })
    @DisplayName("portLinePatter should match to lines of type \"[port] - [service]\"")
    void portLinePattern_should_matchToCorrectLines(String line) {
        boolean matches = line.matches(portLine.pattern());

        assertThat(matches).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "1001 -my service",
        "2513 -  service1",
        "-1 - unknown serviiiice"
    })
    @DisplayName("portLinePatter should not match to lines of not \"[port] - [service]\" type")
    void portLinePattern_should_not_matchToIncorrectLines(String line) {
        boolean matches = line.matches(portLine.pattern());

        assertThat(matches).isFalse();
    }

}
