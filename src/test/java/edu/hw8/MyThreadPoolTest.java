package edu.hw8;

import edu.hw8.task2.MyThreadPool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import static org.assertj.core.api.Assertions.assertThat;

public class MyThreadPoolTest {

    @Test
    @DisplayName("Thead pool should be able to execute tasks asynchronously (for example increment atomic integer)")
    void threadPool_should_executeTasksAsynchronously() {
        var atomicInteger = new AtomicInteger();
        int threadPoolSize = 5;
        int increment = 100;

        try (MyThreadPool threadPool = new MyThreadPool(threadPoolSize)) {

            for(int i =0; i< increment;i++){
                threadPool.execute(atomicInteger::incrementAndGet);
            }

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        assertThat(atomicInteger.get()).isEqualTo(increment);
    }

}
