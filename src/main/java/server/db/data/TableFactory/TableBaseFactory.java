package server.db.data.TableFactory;

import server.db.MediathequeDbService;

import java.sql.SQLException;

public abstract class TableBaseFactory implements TableFactory {
    protected final String createOperation = "crée";
    protected final String removeOperation = "supprimé";

    public static void createAllTables() throws SQLException {
        TableBaseFactory t = new TableAbonne();
        TableBaseFactory t2 = new TableDocument();
        TableBaseFactory t3 = new TableDvd();

        t.createTable();
        t2.createTable();
        t3.createTable();
    }

    public static void dropAllTables() throws SQLException {
        TableBaseFactory t = new TableDvd();
        TableBaseFactory t2 = new TableDocument();
        TableBaseFactory t3 = new TableAbonne();

        t.removeTable();
        t2.removeTable();
        t3.removeTable();
    }

    public void executeQueryCreate(String createTableSQL, String tableName, String operation) throws SQLException {
        MediathequeDbService.executeUpdate(createTableSQL);
//        System.out.println("Table " + tableName + " " + operation + " avec succès !");
    }

    @Override
    public abstract void createTable() throws SQLException;

    public abstract void removeTable() throws SQLException;
}
