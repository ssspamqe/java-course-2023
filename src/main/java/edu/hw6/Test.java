package edu.hw6;

import edu.hw6.Task4.Task4;
import java.io.IOException;

public class Test {

    public static void main(String[] params) throws IOException {
        var a = new Task4();
        a.solution("./sampleFiles/file.txt", "my text");
    }
}
