package edu.hw10.Task2;

import edu.hw10.Task2.cachingWorkers.Cache;

public interface Calculator {
    @Cache(persist = true)
    public long calculate(long a);
}
