package server.Operations.Reservation;

import config.Config;
import server.serv.MediathequeServer;

import java.io.IOException;

public class reservationServer extends MediathequeServer {
    public reservationServer() throws IOException {
        super(Config.getPort("PORT_RESERVATION"), reservationService.class);
    }
}