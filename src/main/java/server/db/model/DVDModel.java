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

        //PhpMyAdmin n'accepte pas true ou false, que 1 ou 0
        int tmpContenuAdulteForDb = documents.isContenuAdulte() ? 1 : 0;

        if (documents.getEntityId() == null) {
            String query = "INSERT INTO dvd (titre, state, abonneId, contenuAdulte) VALUES ('" + documents.getTitre() + "', '" + documents.getState() + "', '" + documents.getAbonneId() + "', '" + tmpContenuAdulteForDb + "')";
            MediathequeDbService.executeUpdate(query);
        } else {
            String query = "UPDATE dvd SET titre = '" + documents.getTitre() + "', state = '" + documents.getState() + "', abonneId = '" + documents.getAbonneId() + "' WHERE numero = " + documents.getEntityId();
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

    @Override
    public void delete(D dataStorage) throws SQLException {
        String query = "DELETE FROM dvd WHERE numero = " + dataStorage.getEntityId();
        MediathequeDbService.executeUpdate(query);
    }
}