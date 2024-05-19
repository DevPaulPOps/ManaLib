package server;

import server.Operations.Emprunt.empruntServer;
import server.Operations.Reservation.reservationServer;
import server.Operations.Retour.retourServer;
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
        try {
            MediathequeServerFactory.createMediathequeServer(serverClasses);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
