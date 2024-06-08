package server.serv;

import server.Operations.Emprunt.Emprunt;
import server.Operations.Reservation.Reservation;
import server.db.data.ManageDataStorage;

import java.util.List;

public class MediathequeServerFactory {

    public static void createMediathequeServer(List<Class<? extends MediathequeServer>> serverClasses) {
        serverClasses.forEach(MediathequeServerFactory::startServer);
    }

    private static void startServer(Class<? extends MediathequeServer> serverClass) {
        try {
            MediathequeServer server = serverClass.getDeclaredConstructor().newInstance();
            new Thread(server).start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteMediathequeServer(List<Class<? extends MediathequeServer>> serverClasses) {
        serverClasses.forEach(MediathequeServerFactory::stopServer);
    }

    private static void stopServer(Class<? extends MediathequeServer> serverClass) {
        try {
            // TODO Save les données
            Reservation.saveData();
            Emprunt.saveData();
            ManageDataStorage.saveDataStorage();

            //Ferme les serveurs
            serverClass.getDeclaredConstructor().newInstance().close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}