package server.db.data.TableFactory;

import java.sql.SQLException;

public interface TableFactory {
    void createTable() throws SQLException;
    void removeTable() throws SQLException;
}
