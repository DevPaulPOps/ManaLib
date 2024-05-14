package server.db;

public abstract class MedhiathequeConnectionDb {

    public static void connection(String jdbcClassDriver, String jdbcUrl) throws ClassNotFoundException {
        MediathequeDbService.setJdbcClassDriver(jdbcClassDriver);
        MediathequeDbService.setJdbcUrl(jdbcUrl);
    }
}
