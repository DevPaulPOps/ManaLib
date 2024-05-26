package server.db.model;

import server.db.MediathequeDbService;
import server.db.data.ManageDataStorage;
import server.elements.Documents.DVD;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DVDModel<D extends DVD> implements Model<D> {

    /**
     * @param documents
     */
    @Override
    public void save(D documents) throws SQLException {
        if (documents.getIdStorage() == null) {
            String query = "INSERT INTO dvd (titre, state, abonneId, contenuAdulte) VALUES ('" + documents.getTitre() + "', '" + documents.getState() + "', '" + documents.getAbonneId() + "', '" + documents.isContenuAdulte() + "')";
            MediathequeDbService.executeUpdate(query);
        } else {
            String query = "UPDATE dvd SET titre = '" + documents.getTitre() + "', state = '" + documents.getState() + "', abonneId = '" + documents.getAbonneId() + "' WHERE id = " + documents.getIdStorage();
            MediathequeDbService.executeUpdate(query);
        }
    }


    /**
     * @throws SQLException
     */
    @Override
    public void getInit() throws SQLException {
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
    }
}