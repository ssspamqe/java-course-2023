package edu.hw9.task2;

import java.nio.file.Path;
import java.util.concurrent.ForkJoinPool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AsyncDFSTest {
    private AsyncDFS dfs = new AsyncDFS(Path.of("./src/test/java/edu/hw9/task2/testFiles/"), 2);
    private ForkJoinPool forkJoinPool = new ForkJoinPool();

    @Test
    @DisplayName("AsyncDFS should return list of directories with >= n files")
    void asyncDFS_should_return_listOfDirectories_withEqualOrGreaterThanNFiles() {
        var paths = forkJoinPool.invoke(dfs);

        assertThat(paths).containsExactlyInAnyOrder(
            Path.of("./src/test/java/edu/hw9/task2/testFiles/d2"),
            Path.of("./src/test/java/edu/hw9/task2/testFiles/d1/d12")
        );
    }

}
