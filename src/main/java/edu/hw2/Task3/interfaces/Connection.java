package edu.hw2.Task3.interfaces;

public interface Connection extends AutoCloseable {
    void execute(String command);
}
