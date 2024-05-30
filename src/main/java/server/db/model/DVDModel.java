package server.db.model;

import server.db.MediathequeDbService;
import server.db.data.ManageDataStorage;
import server.elements.Documents.DVD;
import server.elements.Documents.Document;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DVDModel<D extends DVD> implements Model<D> {
    DocumentModel<Document> documentModel = new DocumentModel();
    int tmpContenuAdulteForDb;

    /**
     * @param documents
     */
    @Override
    public void save(D documents) throws SQLException {

        //PhpMyAdmin n'accepte pas true ou false, que 1 ou 0
        tmpContenuAdulteForDb = documents.isContenuAdulte() ? 1 : 0;

        //Pour l'heritage
        int clefGenereBd = documentModel.saveAndGetKey(documents);

        if (documents.getEntityId() == null) {
            String query = "INSERT INTO dvd (numero, contenuAdulte) VALUES ('" + clefGenereBd + "', '" + tmpContenuAdulteForDb + "')";
            MediathequeDbService.executeUpdate(query);
        } else {
            update(documents);
        }
    }

    @Override
    public void update(D documents) throws SQLException {
        documentModel.update(documents);
        String query = "UPDATE dvd SET contenuAdulte = " + tmpContenuAdulteForDb + " WHERE numero = " + documents.getEntityId();
        MediathequeDbService.executeUpdate(query);
    }

    /**
     * @throws SQLException
     */
    @Override
    public void getInit() throws SQLException {
        String query = "SELECT d.numero, doc.titre, doc.state, doc.abonneId, d.contenuAdulte " +
                "FROM dvd d " +
                "JOIN document doc ON d.numero = doc.numero";

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
        documentModel.delete(dataStorage);
    }
}