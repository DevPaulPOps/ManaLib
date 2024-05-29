package client;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {

        SelectionPortServer.messageBienvenue();

        if (args.length != 1) {
            System.err.println("Services disponible : " + SelectionPortServer.getServices());
            return;
        }

        ClientServer client;
        try {
            client = new ClientServer(SelectionPortServer.getPort(args[0]));
            System.out.println(client.getBttpProtocole().getResponseServer());

            //A voir vu que ducoup on recuperer pas les reponses du serveur
            while (true) {
                client.getBttpProtocole().initInOut();
                client.getBttpProtocole().sendResponseToServer(client.getInput().readLine());
//                if ("exit")
//                    break;
            }
//            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
