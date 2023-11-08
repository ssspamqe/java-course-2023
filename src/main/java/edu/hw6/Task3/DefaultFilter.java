package edu.hw6.Task3;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;

public interface DefaultFilter extends DirectoryStream.Filter<Path> {
    default DefaultFilter and(DefaultFilter other) {
        return (Path path) -> (accept(path) && other.accept(path));
    }
}
