package server.db.model;

import server.db.MediathequeDbService;
import server.db.data.ManageDataStorage;
import server.elements.Documents.DVD;
import server.elements.Documents.Document;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DVDModel<D extends DVD> implements Model<D> {
    DocumentModel<Document> documentModel = new DocumentModel();

    @Override
    public void save(D documents) throws SQLException {
        int tmpContenuAdulteForDb = documents.isContenuAdulte() ? 1 : 0;
        int clefGenereBd = documentModel.saveAndGetKey(documents);

        if (documents.getEntityId() == null) {
            String query = "INSERT INTO dvd (numero, contenuAdulte) VALUES (?, ?)";
            try (PreparedStatement pst = MediathequeDbService.prepareStatement(query)) {
                pst.setInt(1, clefGenereBd);
                pst.setInt(2, tmpContenuAdulteForDb);
                pst.executeUpdate();
            }
        } else {
            update(documents);
        }
    }

    @Override
    public void update(D documents) throws SQLException {
        int tmpContenuAdulteForDb = documents.isContenuAdulte() ? 1 : 0;
        documentModel.update(documents);
        String query = "UPDATE dvd SET contenuAdulte = ? WHERE numero = ?";
        try (PreparedStatement pst = MediathequeDbService.prepareStatement(query)) {
            pst.setInt(1, tmpContenuAdulteForDb);
            pst.setInt(2, documents.getEntityId());
            pst.executeUpdate();
        }
    }

    @Override
    public void getInit() throws SQLException {
        String query = "SELECT d.numero, doc.titre, doc.state, doc.abonneId, d.contenuAdulte " +
                "FROM dvd d " +
                "JOIN document doc ON d.numero = doc.numero";

        try (ResultSet allData = MediathequeDbService.executeQuery(query)) {
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

    @Override
    public void delete(D dataStorage) throws SQLException {
        documentModel.delete(dataStorage);
    }
}