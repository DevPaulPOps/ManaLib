package server.db.data;

import server.db.data.TableFactory.TableBaseFactory;
import server.elements.interfaces.DataStorage;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class ManageDataStorage {
    public static HashMap<String, DataStorage> dataStorage = new HashMap<>();

    public static void initDataStorage() throws SQLException {
        TableBaseFactory.createAllTables();
    }

    public static void addDataStorage(DataStorage dataStorage) {
        ManageDataStorage.dataStorage.put(dataStorage.getIdStorage().toString(), dataStorage);
    }

    public static DataStorage getDataStorageById(String id) {
        return ManageDataStorage.dataStorage.get(id);
    }

    public static List<DataStorage> getAllDataStorage() {
        return (List<DataStorage>) ManageDataStorage.dataStorage.values();
    }

    public static void removeDataStorage(String id) {
        ManageDataStorage.dataStorage.remove(id);
    }

    public static void updateDataStorage(DataStorage dataStorage) {
        ManageDataStorage.dataStorage.put(dataStorage.getIdStorage().toString(), dataStorage);
    }
}
