package server.Operations.Reservation;

import server.serv.MediathequeService;

import java.net.Socket;

public class reservationService extends MediathequeService {

    public reservationService(Socket socket) {
        super(socket);
    }

    @Override
    public void lancement() {
        // TODO
    }
}
