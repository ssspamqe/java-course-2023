package edu.hw6.Task2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Task2 {

    public void cloneFile(Path path) {
        var directory = path.getParent();
        var fileName = path.getFileName();

        var fileData = fileName.toString().split("\\.");
        var baseFileName = fileData[0];
        var fileExtension = fileData[1];

        var newPath = Paths.get(directory.toString(), baseFileName + " - copy." + fileExtension);
        int id = 2;
        while (Files.exists(newPath)) {
            newPath =
                Paths.get(directory.toString(), String.format("%s - copy (%d).%s", baseFileName, id, fileExtension));
            id++;
        }

        try {
            Files.copy(path, newPath);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

}
