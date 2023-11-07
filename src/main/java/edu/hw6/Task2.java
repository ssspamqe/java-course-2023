package edu.hw6;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Task2 {

    public void cloneFile(Path path) {
        var parent = path.getParent();
        var file = path.getFileName();

        var fileData = file.toString().split("\\.");
        var fileName = fileData[0];
        var fileExtension = fileData[1];

        var newPath = Paths.get(parent.toString(), fileName + " - copy." + fileExtension);
        int id = 1;
        while (Files.notExists(newPath)) {
            id++;
            newPath = Paths.get(path.toString(), String.format("%s - copy (%d).%s", fileName, id, fileExtension));
        }

        try{
            Files.copy(path,newPath);
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }

    }

}
