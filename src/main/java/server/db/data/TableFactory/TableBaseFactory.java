package server.db.data.TableFactory;

import server.db.MediathequeDbService;

import java.sql.SQLException;

public abstract class TableBaseFactory implements TableFactory {
    protected final String createOperation = "crée";
    protected final String removeOperation = "supprimé";

    public static void createAllTables() throws SQLException {
        TableBaseFactory[] tables = {new TableAbonne(), new TableDocument(), new TableDvd()};
        for (TableBaseFactory table : tables) {
            table.createTable();
        }
    }

    public static void dropAllTables() throws SQLException {
        TableBaseFactory[] tables = {new TableDvd(), new TableDocument(), new TableAbonne()};
        for (TableBaseFactory table : tables) {
            table.removeTable();
        }
    }

    public void executeQueryCreate(String createTableSQL, String tableName, String operation) throws SQLException {
        MediathequeDbService.executeUpdate(createTableSQL);
//        System.out.println("Table " + tableName + " " + operation + " avec succès !");
    }

    @Override
    public abstract void createTable() throws SQLException;

    public abstract void removeTable() throws SQLException;
}
