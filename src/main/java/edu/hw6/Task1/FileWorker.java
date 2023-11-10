package edu.hw6.Task1;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileWorker {

    private String fullFileName;

    public FileWorker(String fullFileName) {
        this.fullFileName = fullFileName;
    }

    public void appendLine(String newLine) {
        try (var printWriter = new PrintWriter(new FileOutputStream(fullFileName), true)) {
            printWriter.println(newLine);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void appendAllLines(List<String> newLines){
        try (var printWriter = new PrintWriter(new FileOutputStream(fullFileName), true)) {
            newLines.forEach(printWriter::println);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void clear() {
        write("");
    }

    public void write(String newString) {
        try (var printWriter = new PrintWriter(new FileOutputStream(fullFileName), true)) {
            printWriter.write(newString);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<String> getAllLines() {
        try (var linesStream = Files.lines(Path.of(fullFileName))) {
            return linesStream.toList();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public String getFullFileName() {
        return fullFileName;
    }

}
