package edu.hw6;

import edu.hw6.Task1.DiskMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Test {
    public static void main(String[] params) throws IOException {
        var path = Paths.get("./sampleFiles/file.txt");
        var parent = path.getParent();

        var fileName = path.getFileName();
        System.out.println(fileName);


        System.out.println(Files.exists(Paths.get(parent.toString(), "file.txt")));
    }
}
