package server.Operations.Reservation;

import server.serv.MediathequeServer;

public class reservationServer extends MediathequeServer {
    private final static int RESERVATION_PORT = 3000;

    public reservationServer() {
        super(RESERVATION_PORT, this);
    }
}
