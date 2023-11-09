package edu.hw6.Task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {

    private static final String FILE_NAME = "./src/test/java/edu/hw6/Task1/map.txt";

    Map<String, String> map;

    @BeforeEach
    void initMap() {
        try {
            Files.deleteIfExists(Path.of(FILE_NAME));

        } catch (Exception ex) {
            throw new RuntimeException(ex);

        }
        map = new DiskMap(FILE_NAME);
    }

    @Test
    @DisplayName("DiskMap should able to get and return key-value pair")
    void map_should_getAndReturnPairs() {

        String key = "firstKey";
        String value = "firstValue";


        map.put(key,value);
        String returnedVal = map.get(key);

        //change fileWriter object
        assertThat(returnedVal).isEqualTo(value);
    }

}
