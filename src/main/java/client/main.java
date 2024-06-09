package client;

import config.Config;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        Config.loadConfig();

        if (args.length != 1) {
            System.err.println("Services disponibles : " + SelectionPortServer.getServices());
            return;
        }

        SelectionPortServer.messageBienvenueWithtoutServices();

        ClientServer client = null;
        try {
            client = new ClientServer(SelectionPortServer.getPort(args[0]));
            client.launch(args);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
