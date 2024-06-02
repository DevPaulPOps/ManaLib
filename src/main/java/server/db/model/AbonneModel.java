package server.db.model;

import server.db.MediathequeDbService;
import server.db.data.ManageDataStorage;
import server.elements.Abonne;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AbonneModel<A extends Abonne> implements Model<A> {

    @Override
    public void save(A dataStorage) throws SQLException {
        if (dataStorage.getEntityId() == null) {
            String query = "INSERT INTO abonne (nom, dateNaissance) VALUES (?, ?)";
            try (PreparedStatement pst = MediathequeDbService.prepareStatement(query)) {
                pst.setString(1, dataStorage.getNom());
                pst.setDate(2, (Date) dataStorage.getDateDeNaissance());
                pst.executeUpdate();
            }
        } else {
            update(dataStorage);
        }
    }

    @Override
    public void update(A dataStorage) throws SQLException {
        String query = "UPDATE abonne SET nom = ?, dateNaissance = ? WHERE numero = ?";
        try (PreparedStatement pst = MediathequeDbService.prepareStatement(query)) {
            pst.setString(1, dataStorage.getNom());
            pst.setDate(2, (Date) dataStorage.getDateDeNaissance());
            pst.setInt(3, dataStorage.getEntityId());
            pst.executeUpdate();
        }
    }

    @Override
    public A getById(int abonneId) throws SQLException {
        String query = "SELECT * FROM abonne WHERE numero = " + abonneId;
        try (ResultSet data = MediathequeDbService.executeQuery(query)) {
            while (data.next()) {
                int id = data.getInt("numero");
                String nom = data.getString("nom");
                Date dateNaissance = data.getDate("dateNaissance");
                Abonne abonne = new Abonne(id, nom, dateNaissance);

                return (A) abonne;
            }
        }
        return null;
    }

    @Override
    public void getAll() throws SQLException {
        String query = "SELECT * FROM abonne";
        try (ResultSet allData = MediathequeDbService.executeQuery(query)) {
            while (allData.next()) {
                int id = allData.getInt("numero");
                String nom = allData.getString("nom");
                Date dateNaissance = allData.getDate("dateNaissance");
                Abonne abonne = new Abonne(id, nom, dateNaissance);

                ManageDataStorage.addDataStorage(abonne);
            }
        }
    }

    @Override
    public void delete(A dataStorage) throws SQLException {
        String query = "DELETE FROM abonne WHERE numero = ?";
        try (PreparedStatement pst = MediathequeDbService.prepareStatement(query)) {
            pst.setInt(1, dataStorage.getEntityId());
            pst.executeUpdate();
        }
    }
}