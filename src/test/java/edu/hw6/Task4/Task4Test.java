package edu.hw6.Task4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {

    private static final String FILE_PATH = "./src/test/java/edu/hw6/Task4/myFile.txt";

    Task4 task = new Task4();

    @Test
    @DisplayName("Solution should write data to file")
    void solution_should_writeDateToFile() throws IOException {
        var message = "mydata";


        task.writeData(FILE_PATH, message);

        String recordedMessage;
        try (Scanner sc = new Scanner(new File(FILE_PATH))) {
            recordedMessage = sc.nextLine();
        }


        assertThat(recordedMessage).isEqualTo(message);
    }

}
