package edu.hw3;

import edu.hw3.Task8.BackwardIterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class Task8Test {

    @Test
    @DisplayName("BackwardIterator should iterate collection backwardly")
    void check_iteration(){
        List<Integer> iterableList = List.of(1,2,3);
        BackwardIterator<Integer> iterator = new BackwardIterator<>(iterableList);

        List<Integer> iterations = new ArrayList<>();
        while(iterator.hasNext())
            iterations.add(iterator.next());

        assertThat(iterations).isEqualTo(List.of(3,2,1));
    }
}
