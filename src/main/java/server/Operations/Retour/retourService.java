package server.Operations.Retour;

import server.serv.MediathequeService;

import java.net.Socket;

public class retourService extends MediathequeService {

    public retourService(Socket socket) {
        super(socket);
    }

    @Override
    public void lancement() {
        // TODO
    }
}
