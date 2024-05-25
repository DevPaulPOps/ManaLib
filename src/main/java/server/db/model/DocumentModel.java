package server.db.model;

import server.db.MediathequeDbService;
import server.db.data.ManageDataStorage;
import server.elements.Documents.Document;
import server.elements.interfaces.DataStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DocumentModel<D> implements Model {
    /**
     * @param documents
     * // TODO IL faut l'adapter pour les abonnées.
     */
    public void save(Document documents) throws SQLException {
        if (documents.getIdStorage() == null) {
            String query = "INSERT INTO document (titre, state, abonneId) VALUES ('" + documents.getTitre() + "', '" + documents.getState() + "', '" + documents.getAbonneId() + "')";
            MediathequeDbService.executeUpdate(query);
        } else {
            String query = "UPDATE document SET titre = '" + documents.getTitre() + "', state = '" + documents.getState() + "', abonneId = '" + documents.getAbonneId() + "' WHERE id = " + documents.getIdStorage();
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
    public ArrayList<Document> get() throws SQLException{
        ArrayList<Document> lstDocument = new ArrayList<>();
        String query = "SELECT * FROM document";
        ResultSet allData = MediathequeDbService.executeQuery(query);
        while (allData.next()) {
            int numero = allData.getInt("numero");
            String titre = allData.getString("titre");
            String state = allData.getString("state");
            int abonneId = allData.getInt("abonneId");

            Document document = new Document(numero, titre, state, abonneId);
            lstDocument.add(document);
            ManageDataStorage.addDataStorage(document);
        }
        return lstDocument;
    }
}