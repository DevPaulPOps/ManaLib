package client;

import config.Config;
import server.db.MediathequeDbService;

import java.io.IOException;

public class main {
    public static void main(String[] args) {
        Config.loadConfig();

        if (args.length != 1) {
            System.err.println("Services disponibles : " + SelectionPortServer.getServices());
            return;
        }

        SelectionPortServer.messageBienvenueWithtoutServices();

        ClientServer client = null;
        try {
            client = new ClientServer(SelectionPortServer.getPort(args[0]));

            if (args[0].equalsIgnoreCase("reservation")) {
                String line;
                while (!(line = client.getBttpProtocole().getResponse()).equals("FIN_DU_CATALOGUE")) {
                    System.out.println(line);
                }
            } else {
                System.out.println(client.getBttpProtocole().getResponse());
            }

            System.out.println(client.getBttpProtocole().getResponse());

            String responseServer = "";
            while (!responseServer.contains("End")) {
                responseServer = client.getBttpProtocole().communicate(client.getClavierInput());
                System.out.println(responseServer);
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de la communication avec le serveur : " + e.getMessage());
        } finally {
            if (client != null) {
                try {
                    client.close();
                    System.out.println("La connexion a été fermée.");
                } catch (IOException e) {
                    System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
                }
            }
        }
    }
}
