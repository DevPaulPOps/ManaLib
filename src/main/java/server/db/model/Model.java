package server.db.model;

import server.elements.Documents.Document;
import server.elements.interfaces.DataStorage;
import server.elements.interfaces.Documents;

import javax.print.Doc;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Model {
    /*
        * @param dataStorage
        * @throws SQLException
        * Doit etre implementer dans les classes qui implementent cette interface et adapté pour les abonnées.
     */
    void save(DataStorage dataStorage) throws SQLException;

    ArrayList get() throws SQLException;
}
