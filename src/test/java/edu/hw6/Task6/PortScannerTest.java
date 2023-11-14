package edu.hw6.Task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PortScannerTest {

    String portLinePattern = PortScanner.PORT_LINE_PATTERN;

    @Test
    @DisplayName("portLinePatter should match to lines of type \"[port] - [service]\"")
    void portLinePattern_should_matchToCorrectLines() {
        List<String> lines = List.of(
            "1001 - my service",
            "2513 - service1",
            "0 - unknown serviiiice",
            "12 - -asda"
        );

        List<Boolean> matches =
            lines.stream()
                .map(it -> it.matches(portLinePattern))
                .toList();

        assertThat(matches).doesNotContain(false);
    }

    @Test
    @DisplayName("portLinePatter should not match to lines of not \"[port] - [service]\" type")
    void portLinePattern_should_not_matchToIncorrectLines() {
        List<String> lines = List.of(
            "1001 -my service",
            "2513 -  service1",
            "-1 - unknown serviiiice"
        );

        List<Boolean> matches =
            lines.stream()
                .map(it -> it.matches(portLinePattern))
                .toList();

        assertThat(matches).doesNotContain(true);
    }

}
