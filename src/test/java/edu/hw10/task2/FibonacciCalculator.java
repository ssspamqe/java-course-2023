package edu.hw10.task2;

public class FibonacciCalculator implements Calculator
{
    @Override
    public long calculate(long n) {
        long first = 0;
        long second = 1;
        for (int i = 0; i <= n; i++) {
            long next = first + second;
            first = second;
            second = next;
        }
        return second;
    }
}
