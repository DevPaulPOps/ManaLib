package client;

import config.Config;
import java.io.IOException;

public class main {
    public static void main(String[] args) {
        Config.loadConfig();
        SelectionPortServer.messageBienvenueWithtoutServices();

        try {
            ClientServer client = new ClientServer(SelectionPortServer.getPort(args[0]));
            String response;

            do {
                client.getBttpProtocole().initInOut();
                response = client.getClavierInput();
                System.out.println(client.getBttpProtocole().communicate(response));
            } while (!"exit".equalsIgnoreCase(response));

            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}