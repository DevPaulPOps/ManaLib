package client;
//TODO implement this class

import config.Config;
import server.Operations.Emprunt.empruntServer;
import server.Operations.Reservation.reservationServer;
import server.Operations.Retour.retourServer;
import server.db.MediathequeDbService;
import server.db.data.ManageDataStorage;
import server.environment.Environment;

import java.io.IOException;
import java.sql.SQLException;

public class main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

        setConfig();

        if (args.length != 1) {
            System.err.println("Services disponible : " + getServices());
            return;
        }

        String serviceDemande = args[0];

        switch (serviceDemande) {
            case "reservation":
                new Reservation().launch(new reservationServer());
                break;
            case "emprunt":
                new Emprunt().launch(new empruntServer());
                break;
            case "retour":
                new Retour().launch(new retourServer());
                break;
            default:
                System.err.println("Merci de lancer le programme avec un service reconnu : " + getServices());
        }
    }

    private static void messageBienvenue() {
        System.out.println("Bonjour et Bienvenue !!\n");
        System.out.println("Voici les services disponible : \n" + getServices());
        System.out.println("Choissez un service : ");
    }

    private static void setConfig() throws ClassNotFoundException, SQLException {
        MediathequeDbService.setJdbcClassDriver(Environment.DRIVER);
        MediathequeDbService.setJdbcUrl(Environment.URL);
        ManageDataStorage.initDataStorage();
    }

    private static String getServices() {
        return "   - reservation \n" +
                "   - emprunt \n" +
                "   - retour \n";
    }
}
