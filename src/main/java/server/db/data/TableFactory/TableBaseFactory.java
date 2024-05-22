package server.db.data.TableFactory;

import server.db.MediathequeDbService;

import java.sql.SQLException;

public abstract class TableBaseFactory implements TableFactory {
     protected final String createOperation = "crée";
     protected final String removeOperation = "supprimé";

    public void executeQueryCreate(String createTableSQL, String tableName, String operation) throws SQLException {
        MediathequeDbService.executeUpdate(createTableSQL);
        System.out.println("Table " + tableName +  " " + operation + " avec succès !");
    }

    public static void createAllTables() throws SQLException {
        TableBaseFactory t = new TableAbonne();
        TableBaseFactory t2 = new TableDocument();

        t.createTable();
        t2.createTable();
    }

    public static void dropAllTables() throws SQLException {
        TableBaseFactory t = new TableDocument();
        TableBaseFactory t2 = new TableAbonne();

        t.removeTable();
        t2.removeTable();
    }

    @Override
    public abstract void createTable() throws SQLException;
    public abstract void removeTable() throws SQLException;
}
