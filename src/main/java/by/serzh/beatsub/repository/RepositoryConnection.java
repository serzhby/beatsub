package by.serzh.beatsub.repository;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

public class RepositoryConnection {
    private static final Logger logger = LoggerFactory.getLogger(RepositoryConnection.class);

    private static EmbeddedDataSource dataSource;

    private RepositoryConnection() {
    }

    public static synchronized DataSource dataSource() {
        if(dataSource == null) {
            dataSource = new EmbeddedDataSource();
            dataSource.setDatabaseName("beatsub");
            dataSource.setCreateDatabase("true");
//            tryInitializeDb(dataSource);
//            try {
//                java.sql.Connection conn = dataSource.getConnection();
//                MetaDataExporter exporter = new MetaDataExporter();
//                exporter.setPackageName("com.serzh.beatsub.generated");
//                exporter.setTargetFolder(new File("src/main/java"));
//                exporter.setBeanSerializer(new BeanSerializer());
//                exporter.export(conn.getMetaData());
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        }
        return dataSource;
    }

//    private static Connection createConnection() {
//        try {
//
//            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
//            return DriverManager.getConnection(dbURL);
//        } catch (Exception ex) {
//            logger.error("Unable to create DB connection", ex);
//            throw new RuntimeException(ex);
//        }
//    }

    private static void tryInitializeDb(DataSource dataSource) {
        try {
            initializeDb(dataSource.getConnection());
        } catch (IOException | SQLException e) {
            throw new RuntimeException("Error initializing database", e);
        }
    }

    private static void initializeDb(Connection mConnection) throws IOException, SQLException {
        ScriptRunner runner = new ScriptRunner(mConnection, false, false);
        String file = "/sql/init.sql";
        InputStream is = RepositoryConnection.class.getResourceAsStream(file);
        runner.runScript(new BufferedReader(new InputStreamReader(is)));
    }
}
