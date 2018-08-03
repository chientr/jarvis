package jarvis.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Chris Tran
 */
public class DBUtils implements Serializable {

    public static Connection getConnection() throws ClassNotFoundException, SQLException, Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=JarvisDB", "sa", "123456");
        return connection;
    }
}
