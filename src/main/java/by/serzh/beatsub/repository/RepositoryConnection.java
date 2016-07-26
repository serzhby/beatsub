package by.serzh.beatsub.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class RepositoryConnection {
    private static final Logger logger = LoggerFactory.getLogger(RepositoryConnection.class);
    private static String dbURL = "jdbc:derby:beatsub;create=true;";

    private static Connection connection;

    private RepositoryConnection() {
    }

    public static synchronized Connection getConnection() {
        if(connection == null) {
            connection = createConnection();
        }
        return connection;
    }

    private static Connection createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            return DriverManager.getConnection(dbURL);
        } catch (Exception ex) {
            logger.error("Unable to create DB connection", ex);
            throw new RuntimeException(ex);
        }
    }
}
