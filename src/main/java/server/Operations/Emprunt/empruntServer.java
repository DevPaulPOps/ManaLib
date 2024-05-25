package server.Operations.Emprunt;

import Constant.Constants;
import server.serv.MediathequeServer;

import java.io.IOException;

public class empruntServer extends MediathequeServer {
    public empruntServer() throws IOException {
        super(Constants.PORT_EMPRUNT, empruntService.class);
    }
}
