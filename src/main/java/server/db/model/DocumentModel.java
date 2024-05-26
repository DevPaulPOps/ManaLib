package server.db.model;

import server.db.MediathequeDbService;
import server.db.data.ManageDataStorage;
import server.elements.Documents.Document;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentModel<D extends Document> implements Model<D> {

    /**
     * @param documents // TODO IL faut l'adapter pour les abonnées.
     */
    @Override
    public void save(D documents) throws SQLException {
        if (documents.getIdStorage() == null) {
            String query = "INSERT INTO document (titre, state, abonneId) VALUES ('" + documents.getTitre() + "', '" + documents.getState() + "', '" + documents.getAbonneId() + "')";
            MediathequeDbService.executeUpdate(query);
        } else {
            String query = "UPDATE document SET titre = '" + documents.getTitre() + "', state = '" + documents.getState() + "', abonneId = '" + documents.getAbonneId() + "' WHERE id = " + documents.getIdStorage();
            MediathequeDbService.executeUpdate(query);
        }
    }


    /**
     * @throws SQLException
     */
    @Override
    public void getInit() throws SQLException {
        String query = "SELECT * FROM document";
        ResultSet allData = MediathequeDbService.executeQuery(query);
        while (allData.next()) {
            int numero = allData.getInt("numero");
            String titre = allData.getString("titre");
            String state = allData.getString("state");
            int abonneId = allData.getInt("abonneId");

            Document document = new Document(numero, titre, state, abonneId);
            ManageDataStorage.addDataStorage(document);
        }
    }
}