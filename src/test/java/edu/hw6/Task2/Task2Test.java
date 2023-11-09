package edu.hw6.Task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    private static final String PARENT_PATH = "./src/test/java/edu/hw6/Task2";

    Task2 solution = new Task2();

    @Test
    @DisplayName("cloneFile(Path) should make a first copy of file with \"-copy\" in the end ")
    void cloneFile_should_makaASpecialFirstCopy() throws IOException {
        var originalFileName = "originalFile.txt";
        var originalFilePath = Paths.get(PARENT_PATH + "/" + originalFileName);

        var clonedFileName = "originalFile - copy.txt";
        var clonedFilePath = Paths.get(PARENT_PATH + "/" + clonedFileName);

        Files.deleteIfExists(originalFilePath);
        Files.deleteIfExists(clonedFilePath);

        Files.createFile(originalFilePath);
        solution.cloneFile(originalFilePath);

        var clonedFileExists = Files.exists(clonedFilePath);

        assertThat(clonedFileExists).isTrue();
    }

    @Test
    @DisplayName("cloneFile(Path) should make a second copy of file with \"-copy (2)\" in the end ")
    void cloneFile_should_makaASpecialSecondCopy() throws IOException {
        var originalFileName = "originalFile.txt";
        var originalFilePath = Paths.get(PARENT_PATH + "/" + originalFileName);

        var secondClonedFileName = "originalFile - copy (2).txt";
        var secondClonedFilePath = Paths.get(PARENT_PATH + "/" + secondClonedFileName);


        Files.deleteIfExists(originalFilePath);
        Files.deleteIfExists(secondClonedFilePath);

        Files.createFile(originalFilePath);
        solution.cloneFile(originalFilePath);
        solution.cloneFile(originalFilePath);

        var secondClonedFileExists = Files.exists(secondClonedFilePath);

        assertThat(secondClonedFileExists).isTrue();
    }

}
