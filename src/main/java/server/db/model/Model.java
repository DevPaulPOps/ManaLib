package server.db.model;

import server.elements.interfaces.Documents;

import java.sql.SQLException;

public interface Model {
    public void save(Documents documents) throws SQLException;

    public void get(Documents documents) throws SQLException;
}
