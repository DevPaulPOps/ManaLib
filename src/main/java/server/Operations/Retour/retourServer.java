package server.Operations.Retour;

import config.Config;
import server.serv.MediathequeServer;

import java.io.IOException;

public class retourServer extends MediathequeServer {
    public retourServer() throws IOException {
        super(Config.getPort("PORT_RETOUR"), retourService.class);
    }
}
