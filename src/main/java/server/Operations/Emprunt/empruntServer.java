package server.Operations.Emprunt;

import server.serv.MediathequeServer;

import java.io.IOException;

public class empruntServer extends MediathequeServer {
    private final static int EMPRUNT_PORT = 4000;

    public empruntServer() throws IOException {
        super(EMPRUNT_PORT, empruntService.class);
    }
}
