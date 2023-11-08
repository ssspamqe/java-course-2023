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

    public void solution(String fileName, String message) throws IOException {
        try (var outputStream = new FileOutputStream(fileName)) {
            try (var checkedOutputStream = new CheckedOutputStream(outputStream, new CRC32())) {
                try (var bufferedOutputStream = new BufferedOutputStream(checkedOutputStream)) {
                    try (
                        var outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8)
                    ) {
                        try (var printWriter = new PrintWriter(outputStreamWriter)) {
                            printWriter.println(message);
                        }
                    }
                }
            }
        }
    }

}
