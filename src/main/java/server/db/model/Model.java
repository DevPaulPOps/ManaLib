package server.db.model;

import server.elements.Documents.Document;
import server.elements.interfaces.Documents;

import java.sql.SQLException;

public interface Model {
    void save(Document documents) throws SQLException;

    void get() throws SQLException;
}
