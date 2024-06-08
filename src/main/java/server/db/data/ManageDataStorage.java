package server.db.data;

import server.db.data.TableFactory.TableBaseFactory;
import server.elements.Documents.Document;
import server.elements.interfaces.DataStorage;
import server.elements.interfaces.Documents;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ManageDataStorage {
    private static HashMap<String, DataStorage> dataStorage = new HashMap<>();

    public static void initDataStorage() throws SQLException {
        TableBaseFactory.createAllTables();
    }

    public static void addDataStorage(DataStorage dataStorage) {
        ManageDataStorage.dataStorage.put(dataStorage.getEntityId().toString(), dataStorage);
    }

    public static List<DataStorage> getAllDataStorage() {
        return List.copyOf(ManageDataStorage.dataStorage.values());
    }

    public static List<Document> getOnlyDocumentDataStorage() {
        return ManageDataStorage.dataStorage.values().stream()
                .filter(dataStorage -> dataStorage instanceof Documents)
                .map(dataStorage -> (Document) dataStorage)
                .collect(Collectors.toList());
    }

    public static void saveDataStorage() {
        dataStorage.forEach(((s, dataStorage1) -> {
            try {
                dataStorage1.saveFromDB();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }));
    }
}
