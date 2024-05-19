package server.db.model;

public class DVDModel implements Model {
    /**
     * @param documents
     */
    @Override
    public void save(Documents documents) throws SQLException {
        if (documents.getId() == null) {
            String query = "INSERT INTO dvd (titre, state, id_reserveur) VALUES ('" + documents.getTitre() + "', '" + documents.getState() + "', '" + documents.getIdReserveur() + "')";
            MediathequeDbService.executeUpdate(query);
        } else {
            String query = "UPDATE dvd SET titre = '" + documents.getTitre() + "', state = '" + documents.getState() + "', id_reserveur = '" + documents.getIdReserveur() + "' WHERE id = " + documents.getId();
            MediathequeDbService.executeUpdate(query);
        }
    }

    /**
     * @throws SQLException
     */
    @Override
    public void get() throws SQLException {
        String query = "SELECT * FROM dvd";
        ResultSet allData = MediathequeDbService.executeQuery(query);
        while (allData.next()) {
            int id = allData.getInt("id");
            String titre = allData.getString("titre");
            String state = allData.getString("state");
            String idReserveur = allData.getString("id_reserveur");

            DVD dvd = new DVD(id, titre, state, idReserveur);

            ManageDataStorage.addDataStorage(dvd);
        }
    }
}