package edu.hw10.Task2;

import edu.hw10.Task2.cachingWorkers.Cache;

public interface Calculator {
    @Cache
    public long calculate(long a);
}
