package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task1Test {

    Task1 task = new Task1();

    @Test
    @DisplayName("00:00 must return 0")
    void should_return0_when_0MinutesAnd0Seconds() {
        assertThat(task.minutesToSeconds("00:00")).isEqualTo(0);
    }

    @Test
    @DisplayName("Seconds must be under 60")
    void should_throwException_when_secondsGreaterOrEqualThan60() {
        assertThatThrownBy(() -> task.minutesToSeconds("00:60")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Minutes must be non negative")
    void should_throwException_when_minutesNegative() {
        assertThatThrownBy(() -> task.minutesToSeconds("-1:07")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("01:07 must return 67")
    void should_returnTime_When_NormalInput() {
        assertThat(task.minutesToSeconds("01:07")).isEqualTo(67);
    }
}
