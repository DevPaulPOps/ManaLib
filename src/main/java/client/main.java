package client;

import config.Config;

import java.io.BufferedReader;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws RuntimeException {
        Config.loadConfig();

//        if (args.length != 1) {
//            System.err.println("Services disponible : " + SelectionPortServer.getServices());
//            return;
//        }

        //Voir avec le prof pour savoir on passe en parametre et si pas de param on fait quoi

        SelectionPortServer.messageBienvenueWithtoutServices();

        ClientServer client;
        try {
            client = new ClientServer(SelectionPortServer.getPort(args[0]));

            while (true) {
                try {
                    client.getBttpProtocole().initInOut();
                    BufferedReader clavier = client.getBttpProtocole().getClavierInput();
                    client.getBttpProtocole().communicate(clavier.readLine());
                    String response = client.getClavierInput().readLine();
                    if ("exit".equalsIgnoreCase(response)) {
                        break;
                    }

                    System.out.println(client.getBttpProtocole().communicate(response));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
