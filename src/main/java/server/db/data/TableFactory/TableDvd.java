package server.db.data.TableFactory;

import java.sql.SQLException;

public class TableDvd extends TableBaseFactory {

    //Faire heritage
    @Override
    public void createTable() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS DVD ("
                + "numero INT AUTO_INCREMENT PRIMARY KEY, "
                + "titre VARCHAR(255) NOT NULL, "
                + "state VARCHAR(255) NOT NULL, "
                + "abonneId INT NOT NULL, "
                + "contenuAdulte BOOLEAN NOT NULL) ";
        this.executeQueryCreate(createTableSQL, "DVD", this.createOperation);
    }

    @Override
    public void removeTable() throws SQLException {
        String createTableSQL = "DROP TABLE IF EXISTS DVD";
        this.executeQueryCreate(createTableSQL, "DVD", this.removeOperation);
    }
}
