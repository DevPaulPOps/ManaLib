package client;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException, RuntimeException {

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
                    String line;
                    while ((line = client.getBttpProtocole().getResponseServer()) != null) {
                        System.out.println(line);
                    }

                    String response = client.getClavierInput().readLine();

                    if ("exit".equalsIgnoreCase(response)) {
                        break;
                    }

                    client.getBttpProtocole().sendResponseToServer(response);
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
