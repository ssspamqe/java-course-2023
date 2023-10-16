package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    @Test
    @DisplayName("asda")
    void checl(){
        Task2 task = new Task2();

        List<String> out = new ArrayList<>(List.of("((()))", "(())", "()", "()", "(()())"));
        List<String> res = task.clusterize("((()))(())()()(()())");

        assertThat(res).isEqualTo(out);
    }
}
