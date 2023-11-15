package edu.hw6.Task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class PortScannerTest {

    String portLinePattern = PortScanner.PORT_LINE_PATTERN;

    @ParameterizedTest
    @ValueSource(strings = {
        "1001 - my service",
        "2513 - service1",
        "0 - unknown serviiiice",
        "12 - -asda"
    })
    @DisplayName("portLinePatter should match to lines of type \"[port] - [service]\"")
    void portLinePattern_should_matchToCorrectLines(String line) {
        boolean matches = line.matches(portLinePattern);

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
        boolean matches = line.matches(portLinePattern);

        assertThat(matches).isFalse();
    }

}
