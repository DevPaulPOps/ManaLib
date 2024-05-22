package server;

import server.Operations.Emprunt.empruntServer;
import server.Operations.Reservation.reservationServer;
import server.Operations.Retour.retourServer;
import server.db.data.DataFactory;
import server.db.model.AbonneModel;
import server.db.model.DVDModel;
import server.db.model.DocumentModel;
import server.db.model.Model;
import server.serv.MediathequeServer;
import server.serv.MediathequeServerFactory;

import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        List<Class<? extends MediathequeServer>> serverClasses = new ArrayList<>();
        serverClasses.add(empruntServer.class);
        serverClasses.add(reservationServer.class);
        serverClasses.add(retourServer.class);

        List<Class<? extends Model>> dataClasses = new ArrayList<>();
        dataClasses.add(AbonneModel.class);
        dataClasses.add(DocumentModel.class);
        dataClasses.add(DVDModel.class);
        try {
            DataFactory.createDataInAppToDb(dataClasses);
            MediathequeServerFactory.createMediathequeServer(serverClasses);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
