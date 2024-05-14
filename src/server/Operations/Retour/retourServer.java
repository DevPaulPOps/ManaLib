package server.Operations.Retour;

import server.serv.MediathequeServer;

import java.io.IOException;

public class retourServer extends MediathequeServer {
    private final static int RETURN_PORT = 5000;

    public retourServer() throws IOException {
        super(RETURN_PORT, retourService.class);
    }
}
