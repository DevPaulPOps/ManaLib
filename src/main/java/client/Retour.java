package client;

import server.Operations.Retour.retourServer;
import server.serv.MediathequeServer;

import java.io.IOException;

public class Retour {
    public void launch(String host) {
        try {
            MediathequeServer server = new retourServer();
            server.run();
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
