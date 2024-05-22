package server.db.model;

import server.elements.Documents.Document;
import server.elements.interfaces.DataStorage;
import server.elements.interfaces.Documents;

import java.sql.SQLException;

public interface Model {
    /*
        * @param dataStorage
        * @throws SQLException
        * Doit etre implementer dans les classes qui implementent cette interface et adapté pour les abonnées.
     */
    void save(DataStorage dataStorage) throws SQLException;

    void get() throws SQLException;
}
