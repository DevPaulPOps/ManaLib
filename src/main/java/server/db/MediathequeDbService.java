package server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class MediathequeDbService {
    private static String jdbcClassDriver;
    private static String jdbcUrl;
    private static Connection connection = null;

    public static void setJdbcClassDriver(String jdbcClassDriver) throws ClassNotFoundException {
        MediathequeDbService.jdbcClassDriver = jdbcClassDriver;
        Class.forName(jdbcClassDriver);
    }

    public static void setJdbcUrl(String jdbcUrl) {
        MediathequeDbService.jdbcUrl = jdbcUrl;
    }
    public static Connection getConnection() throws SQLException {
        if (MediathequeDbService.connection == null) {

            MediathequeDbService.connection = java.sql.DriverManager.getConnection(jdbcUrl);

        }
        return connection;
    }
    public static void closeConnection() throws SQLException {
        MediathequeDbService.getConnection().close();
    }

    public static PreparedStatement prepareStatement(String query) throws SQLException {
        return MediathequeDbService.getConnection().prepareStatement(query);
    }

    public static void executeUpdate(String query) throws SQLException {
        MediathequeDbService.getConnection().createStatement().executeUpdate(query);
    }

    public static ResultSet executeQuery(String query) throws SQLException {
        return MediathequeDbService.getConnection().createStatement().executeQuery(query);
    }



}
