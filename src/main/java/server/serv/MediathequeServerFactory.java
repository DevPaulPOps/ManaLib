package server.serv;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class MediathequeServerFactory {

    public static void createMediathequeServer(List<Class<? extends MediathequeServer>> serverClasses) {
        for (Class<? extends MediathequeServer> serverClass : serverClasses) {
            try {
                MediathequeServer server = serverClass.getDeclaredConstructor().newInstance();
                new Thread(server).start();
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void deleteMediathequeServer(List<Class<? extends MediathequeServer>> serverClasses) {
        try {
            //Save les données

            //Ferme les serveurs
            for (Class<? extends MediathequeServer> serverClass : serverClasses) {
                serverClass.getDeclaredConstructor().newInstance().close();
            }

        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}