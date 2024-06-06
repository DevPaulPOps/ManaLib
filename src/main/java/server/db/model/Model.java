package server.db.model;

import server.elements.interfaces.DataStorage;

import java.sql.SQLException;

public interface Model<D extends DataStorage> {
    /*
     * @param dataStorage
     * @throws SQLException
     * Doit etre implementer dans les classes qui implementent cette interface et adapté pour les abonnées.
     */
    void save(D dataStorage) throws SQLException;

    void update(D dataStorage) throws SQLException;

    D getById(int id) throws SQLException;

    void getAll() throws SQLException;

    void delete(D dataStorage) throws SQLException;
}
