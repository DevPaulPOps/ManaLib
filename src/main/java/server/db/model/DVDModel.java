package server.db.model;

import server.db.MediathequeDbService;
import server.db.data.ManageDataStorage;
import server.elements.Documents.DVD;
import server.elements.Documents.Document;
import server.elements.interfaces.DataStorage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DVDModel<D extends Document> implements Model {

    /**
     * @param documents
     */
    public void save(Document documents) throws SQLException {
        if (documents.getIdStorage() == null) {
            String query = "INSERT INTO Document (titre, state, abonneId) VALUES ('" + documents.getTitre() + "', '" + documents.getState() + "', '" + documents.getAbonneId() + "')";
            MediathequeDbService.executeUpdate(query);
        } else {
            String query = "UPDATE Document SET titre = '" + documents.getTitre() + "', state = '" + documents.getState() + "', abonneId = '" + documents.getAbonneId() + "' WHERE id = " + documents.getIdStorage();
            MediathequeDbService.executeUpdate(query);
        }
    }

    @Override
    public void save(DataStorage dataStorage) throws SQLException {

    }

    /**
     * @throws SQLException
     */
    @Override
    public void get() throws SQLException {
        String query = "SELECT * FROM Document";
        ResultSet allData = MediathequeDbService.executeQuery(query);
        while (allData.next()) {
            int id = allData.getInt("id");
            String titre = allData.getString("titre");
            String state = allData.getString("state");
            int abonneId = allData.getInt("abonneId");

            DVD dvd = new DVD(id, titre, state, abonneId, false);

            ManageDataStorage.addDataStorage(dvd);
        }
    }
}