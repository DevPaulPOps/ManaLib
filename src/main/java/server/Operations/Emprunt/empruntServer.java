package server.Operations.Emprunt;

import config.Config;
import server.serv.MediathequeServer;

import java.io.IOException;

public class empruntServer extends MediathequeServer {
    public empruntServer() throws IOException {
        super(Config.getPort("PORT_EMPRUNT"), empruntService.class);
    }
}
