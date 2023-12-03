package edu.hw9;

import edu.hw9.task1.MetricType;
import edu.hw9.task1.StatsCollector;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatsCollectorTest {

    private static final double MAX_DELTA = 0.0001;

    private static final int THREADS = 5;

    private StatsCollector collector;

    @BeforeEach
    void initStatsCollector() {
        collector = new StatsCollector(THREADS);
    }

    @Test
    @DisplayName("Stats collector should return list of stats' Future instances")
    void statsCollector_should_return_listOfFuturesStats() throws ExecutionException, InterruptedException {
        var array = new Double[] {1.0, 6.23, 1.3, 6.0, 2.6, 6.1, 23.3, 6.0, 1.02};
        var metricSum = MetricType.SUM;
        var metricMin = MetricType.MIN;
        var metricMax = MetricType.MAX;
        var metricAverage = MetricType.AVERAGE;

        int statSumId = collector.push(metricSum, array);
        int statMinId = collector.push(metricMin, array);
        int statMaxId = collector.push(metricMax, array);
        int statAverageId = collector.push(metricAverage, array);

        var stats = collector.getStats();

        var sumResult = stats.get(statSumId).get();
        var minResult = stats.get(statMinId).get();
        var maxResult = stats.get(statMaxId).get();
        var averageResult = stats.get(statAverageId).get();

        var sum = Arrays.stream(array).mapToDouble(Number::doubleValue).sum();
        var min = Arrays.stream(array).mapToDouble(Number::doubleValue).min().orElseThrow();
        var max = Arrays.stream(array).mapToDouble(Number::doubleValue).max().orElseThrow();
        var average = Arrays.stream(array).mapToDouble(Number::doubleValue).average().orElseThrow();

        assertEquals(sumResult, sum, MAX_DELTA);
        assertEquals(minResult, min, MAX_DELTA);
        assertEquals(maxResult, max, MAX_DELTA);
        assertEquals(averageResult, average, MAX_DELTA);
    }

}
