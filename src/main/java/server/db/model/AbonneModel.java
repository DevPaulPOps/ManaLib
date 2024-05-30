package server.db.model;

import server.db.MediathequeDbService;
import server.db.data.ManageDataStorage;
import server.elements.Abonne;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AbonneModel<A extends Abonne> implements Model<A> {

    /**
     * @param dataStorage
     * @throws SQLException
     */
    @Override
    public void save(A dataStorage) throws SQLException {
        if (dataStorage.getEntityId() == null) {
            String query = "INSERT INTO abonne (nom, dateNaissance) VALUES ('" + dataStorage.getNom() + "', '" + dataStorage.getDateDeNaissance() + "')";
            MediathequeDbService.executeUpdate(query);
        } else {
            String query = "UPDATE abonne SET nom = '" + dataStorage.getNom() + "', dateNaissance = '" + dataStorage.getDateDeNaissance() + "' WHERE numero = " + dataStorage.getEntityId();
            MediathequeDbService.executeUpdate(query);
        }
    }

    /**
     * @throws SQLException
     */
    @Override
    public void getInit() throws SQLException {
        String query = "SELECT * FROM abonne";
        ResultSet allData = MediathequeDbService.executeQuery(query);
        while (allData.next()) {
            int id = allData.getInt("numero");
            String nom = allData.getString("nom");
            Date dateNaissance = allData.getDate("dateNaissance");

            Abonne abonne = new Abonne(id, nom, dateNaissance);

            ManageDataStorage.addDataStorage(abonne);
        }
    }

    @Override
    public void delete(A dataStorage) throws SQLException {
        String query = "DELETE FROM abonne WHERE numero = " + dataStorage.getEntityId();
        MediathequeDbService.executeUpdate(query);
    }
}
