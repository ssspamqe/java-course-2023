package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AsyncFileSearcher extends RecursiveTask<List<Path>> {

    private static final Logger LOGGER = LogManager.getLogger();

    private final Path currentPath;
    private final List<Predicate<Path>> predicates;

    public AsyncFileSearcher(Path currentPath, List<Predicate<Path>> predicates) {
        this.currentPath = currentPath;
        this.predicates = predicates;
    }

    @Override
    protected List<Path> compute() {
        if (!Files.isReadable(currentPath)) {
            return List.of();
        }

        List<AsyncFileSearcher> forks = new ArrayList<>();
        List<Path> result = new ArrayList<>();
        try {
            Files.list(currentPath).forEach(it -> {
                    if (Files.isDirectory(it)) {
                        forks.add(new AsyncFileSearcher(it, predicates));
                        forks.getLast().fork();
                    } else {
                        if (isCorrectFile(it)) {
                            result.add(it);
                        }
                    }
                }
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        forks.forEach(it ->
            result.addAll(it.join())
        );
        return result;
    }

    private boolean isCorrectFile(Path filePath) {
        return predicates.stream().allMatch(it -> it.test(filePath));
    }
}
