package server.Operations.Retour;

import Constant.Constants;
import server.serv.MediathequeServer;

import java.io.IOException;

public class retourServer extends MediathequeServer {
    public retourServer() throws IOException {
        super(Constants.PORT_RETOUR, retourService.class);
    }
}
