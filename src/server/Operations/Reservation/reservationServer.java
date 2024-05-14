package server.Operations.Reservation;

import server.serv.MediathequeServer;

import java.io.IOException;

public class reservationServer extends MediathequeServer {
    private final static int RESERVATION_PORT = 3000;

    public reservationServer() throws IOException {
        super(RESERVATION_PORT, reservationService.class);
    }
}
