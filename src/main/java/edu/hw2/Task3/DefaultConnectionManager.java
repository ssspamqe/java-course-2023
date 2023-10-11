package edu.hw2.Task3;

import edu.hw2.Task3.interfaces.Connection;
import edu.hw2.Task3.interfaces.ConnectionManager;
import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    Random rand = new Random();
    @Override
    public Connection getConnection() {
        if(rand.nextBoolean())
            return new FaultyConnection();
        return new StableConnection();
    }
}
