package server.db.data.TableFactory;

import java.sql.SQLException;

public class TableDvd extends TableBaseFactory {

    //Faire heritage
    @Override
    public void createTable() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS dvd ("
                + "numero INT PRIMARY KEY, "
                + "contenuAdulte BOOLEAN NOT NULL, "
                + "FOREIGN KEY (numero) REFERENCES Document (numero) ON DELETE CASCADE)";
        this.executeQueryCreate(createTableSQL, "DVD", this.createOperation);
    }

    @Override
    public void removeTable() throws SQLException {
        String createTableSQL = "DROP TABLE IF EXISTS DVD";
        this.executeQueryCreate(createTableSQL, "DVD", this.removeOperation);
    }
}
