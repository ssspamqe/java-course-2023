package edu.hw6.Task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public interface DefaultFilter extends DirectoryStream.Filter<Path> {
    default DefaultFilter and(DefaultFilter other) {
        return (Path path) -> (accept(path) && other.accept(path));
    }
}
