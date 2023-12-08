package edu.hw10.task2;

import edu.hw10.Task2.cachingWorkers.Cache;

public interface Calculator {
    @Cache(persist = true)
    long calculate(long a);
}
