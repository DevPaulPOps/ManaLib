package server.db.data;

import server.elements.interfaces.DataStorage;
import server.elements.interfaces.dataStorage;

import java.util.HashMap;
import java.util.List;

public class ManageDataStorage {
    public static HashMap<String, DataStorage> dataStorage = new HashMap<>();

    /**
     * TODO : add dataStorage with an factory
     */
    public static void initDataStorage() {
        //TODO : add dataStorage with an factory
    }

    public static void addDataStorage(DataStorage dataStorage) {
        ManageDataStorage.dataStorage.put(dataStorage.getId(), dataStorage);
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
        ManageDataStorage.dataStorage.put(dataStorage.getId(), dataStorage);
    }
}
