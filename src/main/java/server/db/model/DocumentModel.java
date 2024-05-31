package server.db.model;

import server.db.MediathequeDbService;
import server.db.data.ManageDataStorage;
import server.elements.Documents.Document;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentModel<D extends Document> implements Model<D> {

    @Override
    public void save(D documents) throws SQLException {
        if (documents.getEntityId() == null) {
            String query = "INSERT INTO document (titre, state, abonneId) VALUES (?, ?, ?)";
            try (PreparedStatement pst = MediathequeDbService.prepareStatement(query)) {
                pst.setString(1, documents.getTitre());
                pst.setString(2, documents.getState());
                pst.setInt(3, documents.getAbonneId());
                pst.executeUpdate();
            }
        } else {
            update(documents);
        }
    }

    public void update(D documents) throws SQLException {
        String query = "UPDATE document SET titre = ?, state = ?, abonneId = ? WHERE numero = ?";
        try (PreparedStatement pst = MediathequeDbService.prepareStatement(query)) {
            pst.setString(1, documents.getTitre());
            pst.setString(2, documents.getState());
            pst.setInt(3, documents.getAbonneId());
            pst.setInt(4, documents.getEntityId());
            pst.executeUpdate();
        }
    }

    public int saveAndGetKey(D documents) throws SQLException {
        if (documents.getEntityId() == null) {
            String query = "INSERT INTO document (titre, state, abonneId) VALUES (?, ?, ?)";
            try (PreparedStatement pst = MediathequeDbService.prepareStatementWithKey(query)) {
                pst.setString(1, documents.getTitre());
                pst.setString(2, documents.getState());
                pst.setInt(3, documents.getAbonneId());
                pst.executeUpdate();

                try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Creating document failed, no ID obtained.");
                    }
                }
            }
        }
        return documents.getEntityId();
    }

    @Override
    public void getInit() throws SQLException {
        String query = "SELECT * FROM document";
        try (ResultSet allData = MediathequeDbService.executeQuery(query)) {
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

    @Override
    public void delete(D dataStorage) throws SQLException {
        String query = "DELETE FROM document WHERE numero = ?";
        try (PreparedStatement pst = MediathequeDbService.prepareStatement(query)) {
            pst.setInt(1, dataStorage.getEntityId());
            pst.executeUpdate();
        }
    }
}