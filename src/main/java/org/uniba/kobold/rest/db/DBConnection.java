package org.uniba.kobold.rest.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The type Db connection.
 */
public class DBConnection {
    private static final String DB_NAME = "map";
    private static final String JDBC_URL = "jdbc:h2:~/" + DB_NAME;
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    // Singleton instance
    private static Connection connection = null;

    private DBConnection() { }

    /**
     * Gets connection.
     *
     * @return the connection
     */
// Method to get the singleton instance of the connection
    public static Connection getConnection() {
        if (connection == null) {
            synchronized (DBConnection.class) {
                if (connection == null) {
                    try {
                        connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }

    /**
     * Close connection.
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connection = null;
            }
        }
    }

}
