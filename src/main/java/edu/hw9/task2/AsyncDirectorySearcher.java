package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AsyncDirectorySearcher extends RecursiveTask<List<Path>> {

    private static final Logger LOGGER = LogManager.getLogger();

    private final Path currentPath;
    private final int minFilesInDirectory;

    public AsyncDirectorySearcher(Path currentPath, int minFilesInDirectory) {
        this.currentPath = currentPath;
        this.minFilesInDirectory = minFilesInDirectory;
    }

    @Override
    protected List<Path> compute() {
        if (!Files.isReadable(currentPath)) {
            return List.of();
        }

        List<AsyncDirectorySearcher> forks = new ArrayList<>();
        AtomicInteger files = new AtomicInteger();
        try {
            Files.list(currentPath).forEach(it -> {
                    if (Files.isDirectory(it)) {
                        forks.add(new AsyncDirectorySearcher(it, minFilesInDirectory));
                        forks.getLast().fork();
                    } else {
                        files.getAndIncrement();
                    }
                }
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Path> result = new ArrayList<>();
        if (files.get() >= minFilesInDirectory) {
            result.add(currentPath);
        }
        forks.forEach(it ->
            result.addAll(it.join())
        );
        return result;
    }
}
