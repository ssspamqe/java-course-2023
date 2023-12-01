package edu.hw9.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.concurrent.ForkJoinPool;

import static org.assertj.core.api.Assertions.assertThat;

public class AsyncDirectorySearcherTest {

    private static final Path ROOT_DIRECTORY = Path.of("./src/test/java/edu/hw9/task2/testFiles/");
    private static final int MIN_FILES_IN_DIRECTORY = 2;

    private AsyncDirectorySearcher directorySearcher;

    private ForkJoinPool forkJoinPool = new ForkJoinPool();

    @Test
    @DisplayName("AsyncDFS should return list of directories with >= n files")
    void asyncDFS_should_return_listOfDirectories_withEqualOrGreaterThanNFiles() {
        directorySearcher = new AsyncDirectorySearcher(ROOT_DIRECTORY, MIN_FILES_IN_DIRECTORY);

        var paths = forkJoinPool.invoke(directorySearcher);

        assertThat(paths).containsExactlyInAnyOrder(
            Path.of("./src/test/java/edu/hw9/task2/testFiles/d2"),
            Path.of("./src/test/java/edu/hw9/task2/testFiles/d1/d12")
        );
    }
}
