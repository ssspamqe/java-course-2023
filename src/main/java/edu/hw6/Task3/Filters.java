package edu.hw6.Task3;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class Filters {

    public static final DefaultFilter REGULAR_FILE = Files::isRegularFile;

    public static final DefaultFilter READABLE = Files::isReadable;
    public static final DefaultFilter WRITEABLE = Files::isWritable;

    private Filters() {
    }

    public static DefaultFilter largerThan(int min) {
        return path -> Files.size(path) >= min;
    }

    public static DefaultFilter magicNumber(char... bytes) {
        return path -> {
            var fileBytes = Files.readAllBytes(path);
            if (bytes.length > fileBytes.length) {
                return false;
            }

            for (int i = 0; i < bytes.length; i++) {
                if (bytes[i] != fileBytes[i]) {
                    return false;
                }
            }
            return true;
        };
    }

    public static DefaultFilter globMatcher(String glob) {
        return (Path path) -> {
            var splitRegex = "\\.";

            var neededFileExtension = glob.split(splitRegex)[1];
            var realFileExtension = new File(path.toString()).getName().split(splitRegex)[1];

            return realFileExtension.equals(neededFileExtension);
        };
    }

    public static DefaultFilter regexMatches(String regex) {
        return (Path path) -> {
            var pattern = Pattern.compile(regex);
            var fileName = new File(path.toString()).getName();
            var matcher = pattern.matcher(fileName);

            return matcher.matches();
        };
    }

}
