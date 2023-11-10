package edu.hw6.Task1;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileWorker {

    private String fullFileName;

    public FileWorker(String fullFileName) {
        this.fullFileName = fullFileName;
    }

    public void appendLine(String newLine) {
        try (var file = new RandomAccessFile(fullFileName,"rw")) {
            addLine(newLine,file);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void appendAllLines(List<String> newLines){
        try (var file = new RandomAccessFile(fullFileName,"rw")) {
            newLines.forEach(line -> addLine(line,file));

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    private void addLine(String newLine, RandomAccessFile file){
        try{
            int fileSize = (int)Files.size(Path.of(fullFileName));
            file.seek(fileSize);
            if(fileSize != 0)
                file.writeBytes("\n");
            file.writeBytes(newLine);

        } catch (Exception ex){
            throw new RuntimeException(ex);
        }

    }

    public void clear() {
        write("");
    }

    public void write(String newString) {
        try (var file = new RandomAccessFile(fullFileName,"rw")) {
            file.writeBytes(newString);

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
