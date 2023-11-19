package edu.hw6.Task4;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

public class Task4 {

    public void writeData(String fileName, String message) throws IOException {
        try (var outputStream = new FileOutputStream(fileName)) {
            var checkedOutputStream = new CheckedOutputStream(outputStream, new CRC32());
            var bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
            var outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8);
            var printWriter = new PrintWriter(outputStreamWriter);

            printWriter.println(message);

            printWriter.close();
            outputStreamWriter.close();
            bufferedOutputStream.close();
            checkedOutputStream.close();
        }
    }

}
