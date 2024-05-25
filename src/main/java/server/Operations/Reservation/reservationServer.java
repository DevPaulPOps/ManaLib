package server.Operations.Reservation;

import Constant.Constants;
import server.serv.MediathequeServer;

import java.io.IOException;

public class reservationServer extends MediathequeServer {
    public reservationServer() throws IOException {
        super(Constants.PORT_RESERVATION, reservationService.class);
    }
}