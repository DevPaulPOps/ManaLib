package server.db.data.TableFactory;

import java.sql.SQLException;

public class TableAbonne extends TableBaseFactory {

    public void createTable() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Abonne ("
                + "numero INT AUTO_INCREMENT PRIMARY KEY, "
                + "nom VARCHAR(100) NOT NULL, "
                + "dateNaissance VARCHAR(100) NOT NULL) ";
        this.executeQueryCreate(createTableSQL, "Abonne", this.createOperation);
    }

    @Override
    public void removeTable() throws SQLException {
        String createTableSQL = "DROP TABLE IF EXISTS Abonne";
        this.executeQueryCreate(createTableSQL, "Abonne", this.removeOperation);
    }
}
