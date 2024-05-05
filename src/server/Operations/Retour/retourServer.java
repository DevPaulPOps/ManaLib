package server.Operations.Retour;

import server.serv.MediathequeServer;

public class retourServer extends MediathequeServer {
    private final static int RETURN_PORT = 5000;

    public retourServer() {
        super(RETURN_PORT, retourServer.class);
    }
}
