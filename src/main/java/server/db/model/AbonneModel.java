package server.db.model;

import server.db.MediathequeDbService;
import server.db.data.ManageDataStorage;
import server.elements.Abonne;
import server.elements.interfaces.Documents;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AbonneModel<A> implements Model{
    /**
     * @param documents
     * @throws SQLException
     */
    @Override
    public void save(Documents documents) throws SQLException {

    }

    /**
     * @throws SQLException
     */
    @Override
    public void get() throws SQLException {
        String query = "SELECT * FROM abonne";
        ResultSet allData = MediathequeDbService.executeQuery(query);
        while (allData.next()) {
            int id = allData.getInt("id");
            String nom = allData.getString("nom");
            Date dateNaissance = allData.getDate("date_naissance");

            Abonne abonne = new Abonne(id, nom, dateNaissance);

            ManageDataStorage.addDataStorage(abonne);
        }
    }
}
