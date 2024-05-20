package server.db.data;

import server.db.model.Model;
import server.elements.interfaces.DataStorage;

import java.util.List;

public class DataFactory {
    public static void createDataInAppToDb(List<Class<? extends Model>> data) {
        try {
            for (Class<? extends Model> d : data) {
                try {
                    Model modelInstance = d.newInstance();
                    modelInstance.get();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createDataInDbToApp(List<Class<? extends Model>> data) {
        try {
            for (Class<? extends Model> d : data) {
                try {
                    Model modelInstance = d.newInstance();
//                    modelInstance.save();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}