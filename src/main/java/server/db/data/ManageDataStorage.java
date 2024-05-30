package server.db.data;

import server.db.data.TableFactory.TableBaseFactory;
import server.elements.Documents.Document;
import server.elements.interfaces.DataStorage;
import server.elements.interfaces.Documents;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManageDataStorage {
    public static HashMap<String, DataStorage> dataStorage = new HashMap<>();

    public static void initDataStorage() throws SQLException {
        TableBaseFactory.createAllTables();
    }

    public static void addDataStorage(DataStorage dataStorage) {
        ManageDataStorage.dataStorage.put(dataStorage.getEntityId().toString(), dataStorage);
    }

    public static DataStorage getDataStorageById(String id) {
        return ManageDataStorage.dataStorage.get(id);
    }

    public static List<DataStorage> getAllDataStorage() {
        return (List<DataStorage>) ManageDataStorage.dataStorage.values();
    }

    public static ArrayList<Document> getOnlyDocumentDataStorage() {
        ArrayList<Document> documents = new ArrayList<>();
        for (DataStorage dataStorage : ManageDataStorage.dataStorage.values()) {
            if (dataStorage instanceof Documents) {
                documents.add((Document) dataStorage);
            }
        }
        return documents;
    }

    public static void removeDataStorage(String id) {
        ManageDataStorage.dataStorage.remove(id);
    }

    public static void updateDataStorage(DataStorage dataStorage) {
        ManageDataStorage.dataStorage.put(dataStorage.getEntityId().toString(), dataStorage);
    }
}
