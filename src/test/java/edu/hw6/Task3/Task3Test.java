package edu.hw6.Task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw6.Task3.Filters.READABLE;
import static edu.hw6.Task3.Filters.REGULAR_FILE;
import static edu.hw6.Task3.Filters.globMatcher;
import static edu.hw6.Task3.Filters.largerThan;
import static edu.hw6.Task3.Filters.magicNumber;
import static edu.hw6.Task3.Filters.regexMatches;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {

    private static final Path TEST_FILES_PATH = Paths.get("./src/test/java/edu/hw6/Task3/TestFiles");

    @Test
    @DisplayName("DirectoryStream.filter implementation should filter files")
    void directoryStreamFilter_should_filterFiles() throws IOException {
        var correctFileName = "correct11File.text";
        DirectoryStream.Filter<Path> filter = REGULAR_FILE
            .and(READABLE)
            .and(largerThan(40 * 1024))
            .and(magicNumber('1', '2', '3'))
            .and(globMatcher("*.text"))
            .and(regexMatches("\\w+\\d+.+"));

        var returnedFiles = new ArrayList<String>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_FILES_PATH, filter)) {
            entries.forEach(filePath ->
                returnedFiles.add(
                    filePath.getFileName().toString()
                )
            );
        }

        assertThat(returnedFiles).containsExactly(correctFileName);
    }

}
