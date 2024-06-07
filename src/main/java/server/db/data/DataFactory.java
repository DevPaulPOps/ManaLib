package server.db.data;

import server.db.model.Model;
import server.elements.interfaces.DataStorage;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class DataFactory {

    public static void createDataInAppToDb(List<Class<? extends Model>> data) {
        for (Class<? extends Model> d : data) {
            try {
                Model modelInstance = d.getDeclaredConstructor().newInstance();
                modelInstance.getAll();
            } catch (InstantiationException | IllegalAccessException | SQLException e) {
                e.printStackTrace();
            } catch (InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /*
     * Il faut passer par les éléments pour pouvoir les ajouter et non pas les models...
     * Donc Save un Document
     */
    public static void createDataInDbToApp() {
        for (DataStorage d : ManageDataStorage.getAllDataStorage()) {
            try {
                d.saveFromDB();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}