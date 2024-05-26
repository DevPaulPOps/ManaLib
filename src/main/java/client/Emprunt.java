package client;

import server.Operations.Emprunt.empruntServer;
import server.serv.MediathequeServer;

import java.io.IOException;

public class Emprunt {
    public void launch(String host) {
        try {
            MediathequeServer server = new empruntServer();
            server.run();
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
