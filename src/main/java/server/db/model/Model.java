package server.db.model;

import server.elements.interfaces.Documents;

import java.sql.SQLException;

public interface Model {
    void save(Documents documents) throws SQLException;

    void get() throws SQLException;
}
