package server.elements.interfaces;

import java.sql.SQLException;

public interface DataStorage {
    Integer getIdStorage();

    /**
     * Methode pour sauvegarder les données present dans la base de données vers l'application
     *
     * @throws SQLException
     */
    void saveFromDB() throws SQLException;
}
