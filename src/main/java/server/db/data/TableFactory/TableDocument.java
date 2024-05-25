package server.db.data.TableFactory;

import java.sql.SQLException;

public class TableDocument extends TableBaseFactory {

    @Override
    public void createTable() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Document ("
                + "numero INT AUTO_INCREMENT PRIMARY KEY, "
                + "titre VARCHAR(255) NOT NULL, "
                + "state VARCHAR(255) NOT NULL, "
                + "abonneId INT NOT NULL, "
                + "FOREIGN KEY (abonneId) REFERENCES Abonne(numero) ON DELETE CASCADE)";
        this.executeQueryCreate(createTableSQL, "Document", this.createOperation);
    }

    @Override
    public void removeTable() throws SQLException {
        String createTableSQL = "DROP TABLE IF EXISTS Document";
        this.executeQueryCreate(createTableSQL, "Document", this.removeOperation);
    }
}
