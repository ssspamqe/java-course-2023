package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AsyncFileSearcherTest {

    private static final Path ROOT_DIRECTORY = Path.of("./src/test/java/edu/hw9/task2/testFiles/");

    AsyncFileSearcher fileSearcher;

    @Test
    @DisplayName("AsyncFileSearcher should return files by predicates")
    void asyncFileSearcher_should_findFiles_byPredicates() {
        List<Predicate<Path>> predicates = List.of(path -> {
            try {
                return Files.size(path) >= 1;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        fileSearcher = new AsyncFileSearcher(ROOT_DIRECTORY, predicates);

        ForkJoinPool pool = new ForkJoinPool();
        var files = pool.invoke(fileSearcher);

        assertThat(files).containsExactlyInAnyOrder(
            Path.of("./src/test/java/edu/hw9/task2/testFiles/file7"),
            Path.of("./src/test/java/edu/hw9/task2/testFiles/d1/d12/file2")
        );
    }

}
