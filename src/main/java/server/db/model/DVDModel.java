package server.db.model;

import server.db.MediathequeDbService;
import server.db.data.ManageDataStorage;
import server.elements.Documents.DVD;
import server.elements.Documents.Document;
import server.elements.interfaces.DataStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DVDModel<D extends Document> implements Model {

    /**
     * @param documents
     */
    public void save(DVD documents) throws SQLException {
        if (documents.getIdStorage() == null) {
            String query = "INSERT INTO dvd (titre, state, abonneId, contenuAdulte) VALUES ('" + documents.getTitre() + "', '" + documents.getState() + "', '" + documents.getAbonneId() + "', '" + documents.isContenuAdulte() + "')";
            MediathequeDbService.executeUpdate(query);
        } else {
            String query = "UPDATE dvd SET titre = '" + documents.getTitre() + "', state = '" + documents.getState() + "', abonneId = '" + documents.getAbonneId() + "' WHERE id = " + documents.getIdStorage();
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
    public ArrayList<Document> get() throws SQLException {
        String query = "SELECT * FROM dvd";
        ResultSet allData = MediathequeDbService.executeQuery(query);
        while (allData.next()) {
            int numero = allData.getInt("numero");
            String titre = allData.getString("titre");
            String state = allData.getString("state");
            int abonneId = allData.getInt("abonneId");
            boolean contenuAdulte = allData.getBoolean("contenuAdulte");

            DVD dvd = new DVD(numero, titre, state, abonneId, contenuAdulte);

            ManageDataStorage.addDataStorage(dvd);
        }
        return new ArrayList<>();
    }
}