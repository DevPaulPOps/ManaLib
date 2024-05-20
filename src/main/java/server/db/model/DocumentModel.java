package server.db.model;

import server.db.MediathequeDbService;
import server.db.data.ManageDataStorage;
import server.elements.Documents.Document;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentModel implements Model{
    /**
     * @param documents
     */
    @Override
    public void save(Document documents) throws SQLException {
        if (documents.getIdStorage() == null) {
            String query = "INSERT INTO document (titre, state, id_reserveur) VALUES ('" + documents.getTitre() + "', '" + documents.getState() + "', '" + documents.getAbonneId() + "')";
            MediathequeDbService.executeUpdate(query);
        } else {
            String query = "UPDATE document SET titre = '" + documents.getTitre() + "', state = '" + documents.getState() + "', id_reserveur = '" + documents.getAbonneId() + "' WHERE id = " + documents.getIdStorage();
            MediathequeDbService.executeUpdate(query);
        }
    }

    /**
     * @throws SQLException
     */
    @Override
    public void get() throws SQLException{
        String query = "SELECT * FROM document";
        ResultSet allData = MediathequeDbService.executeQuery(query);
        while (allData.next()) {
            int id = allData.getInt("id");
            String titre = allData.getString("titre");
            String state = allData.getString("state");
            int idReserveur = allData.getInt("id_reserveur");

            Document document = new Document(id, titre, state, idReserveur);

            ManageDataStorage.addDataStorage(document);
        }
    }
}