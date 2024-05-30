package client;

import config.Config;

import java.util.Scanner;

public class SelectionPortServer {

    public static void messageBienvenue() {
        System.out.println("Bonjour et Bienvenue !!\n");
        System.out.println("Voici les services disponible : \n" + getServices());
        System.out.println("Choisissez un service : ");
    }

    public static void messageBienvenueWithtoutServices() {
        System.out.println("Bonjour,\n");
    }

    public static int getPort(String serviceDemande) {
        while (true) {
            int port = getPortChoosed(serviceDemande);
            if (port != -1) {
                return port;
            } else {
                System.err.println("Service non reconnu. Veuillez réessayer.");
                System.out.println("Veuillez saisir un service parmi les suivants : " + getServices());
                Scanner sc = new Scanner(System.in);
                serviceDemande = sc.nextLine();
            }
        }
    }

    public static int getPortChoosed(String serviceDemande) {
        return switch (serviceDemande) {
            case "reservation" -> Config.getPort("PORT_RESERVATION");
            case "emprunt" -> Config.getPort("PORT_EMPRUNT");
            case "retour" -> Config.getPort("PORT_RETOUR");
            default -> {
                System.err.println("Merci de lancer le programme avec un service reconnu : " + getServices());
                yield -1;
            }
        };
    }

    public static String getServices() {
        return  "   - reservation \n" +
                "   - emprunt \n" +
                "   - retour \n";
    }
}
