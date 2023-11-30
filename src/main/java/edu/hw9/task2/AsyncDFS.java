package edu.hw9.task2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AsyncDFS extends RecursiveTask<List<Path>> {

    private static final Logger LOGGER = LogManager.getLogger();

    private final Path currentPath;
    private final int minFilesInDirectory;

    public AsyncDFS(Path currentPath, int minFilesInDirectory) {
        this.currentPath = currentPath;
        this.minFilesInDirectory = minFilesInDirectory;
    }

    @SneakyThrows @Override
    protected List<Path> compute() {
        if (!Files.isReadable(currentPath)) {
            return List.of();
        }

        List<AsyncDFS> forks = new ArrayList<>();
        AtomicInteger files = new AtomicInteger();
        Files.list(currentPath).forEach(it -> {
                if (Files.isDirectory(it)) {
                    forks.add(new AsyncDFS(it, minFilesInDirectory));
                    forks.getLast().fork();
                } else {
                    files.getAndIncrement();
                }
            }
        );

        List<Path> result = new ArrayList<>();
        if (files.get() >= minFilesInDirectory) {
            result.add(currentPath);
        }
        forks.forEach(it -> {
            result.addAll(it.join());
        });
        return result;
    }
}
