package Controller;

import Model.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static SQLException exception = null;

    public static Connection getConnection(Database database) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(database.getUrl(), database.getUser(), database.getPass());
        } catch (SQLException e) {
            exception = e;
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
