package edu.hw2.Task3;

import edu.hw2.Task3.interfaces.Connection;
import edu.hw2.Task3.interfaces.ConnectionManager;

public class FaultyConnectionManager implements ConnectionManager {

    @Override
    public Connection getConnection() {
        return new FaultyConnection();
    }
}
