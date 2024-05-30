package server.db;

import java.sql.*;

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

    public static void setJdbcUrlClassDriver(String jdbcUrl, String jdbcClassDriver) throws ClassNotFoundException {
        setJdbcUrl(jdbcUrl);
        setJdbcClassDriver(jdbcClassDriver);
    }

    public static void closeConnection() throws SQLException {
        MediathequeDbService.getConnection().close();
    }

    public static PreparedStatement prepareStatement(String query) throws SQLException {
        return MediathequeDbService.getConnection().prepareStatement(query);
    }

    public static PreparedStatement prepareStatementWithKey(String query) throws SQLException {
        return MediathequeDbService.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    }

    public static int executeUpdateKey(String query) throws SQLException {
        PreparedStatement pst = MediathequeDbService.prepareStatementWithKey(query);
        pst.executeUpdate();

        try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating document failed, no ID obtained.");
            }
        }
    }

    public static void executeUpdate(String query) throws SQLException {
        MediathequeDbService.getConnection().createStatement().executeUpdate(query);
    }

    public static ResultSet executeQuery(String query) throws SQLException {
        return MediathequeDbService.getConnection().createStatement().executeQuery(query);
    }
}
