package server.Operations.Emprunt;

import server.serv.MediathequeServer;

public class empruntServer extends MediathequeServer {
    private final static int EMPRUNT_PORT = 4000;

    public empruntServer() {
        super(EMPRUNT_PORT, empruntServer.class);
    }
}
