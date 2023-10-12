package edu.hw2;

import edu.hw2.Task3.DefaultConnectionManager;
import edu.hw2.Task3.FaultyConnection;
import edu.hw2.Task3.FaultyConnectionManager;
import edu.hw2.Task3.PopularCommandExecutor;
import edu.hw2.Task3.interfaces.Connection;
import edu.hw2.Task3.interfaces.ConnectionManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {

    @Test
    @DisplayName("DefaultConnectionManager should return Connection interface implementation instance")
    void check_DefaultConnectionManager_getConnection() {
        ConnectionManager connectionManager = new DefaultConnectionManager();
        assertThat(connectionManager.getConnection()).isInstanceOf(Connection.class);
    }

    @Test
    @DisplayName("FaultyConnectionManager should return FaultyConnection instance")
    void check_FaultyConnectionManager_getConnection() {
        ConnectionManager connectionManager = new FaultyConnectionManager();
        assertThat(connectionManager.getConnection()).isInstanceOf(FaultyConnection.class);
    }
}
