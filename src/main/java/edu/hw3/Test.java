package edu.hw3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {
    public static void main(String[] params) {
        List<String> test = new ArrayList<>(List.of("a", "c", "b"));
        Collections.sort(test);
        test.forEach(System.out::println);
    }
}
