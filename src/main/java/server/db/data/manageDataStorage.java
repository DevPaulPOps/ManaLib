package server.db.data;

import server.elements.interfaces.dataStorage;

import java.util.HashMap;
import java.util.List;

public class manageDataStorage {
    public static HashMap<String, dataStorage> dataStorage = new HashMap<>();

    /**
     * TODO : add dataStorage with an factory
     */
    public static void initDataStorage() {
        //TODO : add dataStorage with an factory
    }

    public static void addDataStorage(dataStorage dataStorage) {
        manageDataStorage.dataStorage.put(dataStorage.getId(), dataStorage);
    }

    public static dataStorage getDataStorageById(String id) {
        return manageDataStorage.dataStorage.get(id);
    }

    public static List<dataStorage> getAllDataStorage() {
        return (List<dataStorage>) manageDataStorage.dataStorage.values();
    }

    public static void removeDataStorage(String id) {
        manageDataStorage.dataStorage.remove(id);
    }

    public static void updateDataStorage(dataStorage dataStorage) {
        manageDataStorage.dataStorage.put(dataStorage.getId(), dataStorage);
    }
}
