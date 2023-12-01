package edu.hw7;

import edu.hw7.Task4.simulation.SimulationResult;
import edu.hw7.Task4.simulation.Simulator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MonteCarloSimulatorTest {

    private static final int THREADS = 2;
    private static final int POINTS = 1_000_000;
    private static final int SIMULATIONS = 5;
    private static final Simulator SIMULATOR = new Simulator();

    @Test
    @DisplayName("Delta should be not greater than 0.1")
    void delta_should_be_notGreaterThan0_1() {
        int pointsPerThread = POINTS / THREADS;
        SimulationResult result = SIMULATOR.getAsyncResult(SIMULATIONS, THREADS, pointsPerThread);
        double delta = result.averageDelta();

        assertThat(delta).isLessThan(0.1);
    }

    @Test
    @DisplayName("Multithread time should be less than single thread time")
    void multiThreadTime_should_be_lessThan_singleThreadTime() {
        SimulationResult singleThreadResult = SIMULATOR.getAsyncResult(SIMULATIONS, 1, POINTS);
        double singleThreadTime = singleThreadResult.averageExecutionTime();

        int pointsPerThread = POINTS / THREADS;
        SimulationResult multiThreadResult = SIMULATOR.getAsyncResult(SIMULATIONS, THREADS, pointsPerThread);
        double multiThreadTime = multiThreadResult.averageExecutionTime();

        assertThat(multiThreadTime).isLessThanOrEqualTo(singleThreadTime);
    }

}
