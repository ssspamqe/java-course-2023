package edu.hw3;

import java.util.TreeMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {

    Task7 comparator = new Task7();

    @Test
    @DisplayName("TreeMap should be able to have null value as key")
    void check_nullAsKey() {
        TreeMap<String, String> map = new TreeMap<>(comparator);

        map.put(null, "test");
        boolean containsNull = map.containsKey(null);

        assertThat(containsNull).isTrue();
    }
}
