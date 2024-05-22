package client;
//TODO implement this class

import server.serv.bttp.BttpProtocole;

import java.io.IOException;
import java.net.Socket;

public class clientServer {
    private final int port = 3000;
    private final String host = "localhost";
    private final BttpProtocole bttpProtocole;

    public clientServer(String host, int port) throws IOException {
        this.bttpProtocole = new BttpProtocole(new Socket(host, port));
    }

    public void close() {
        bttpProtocole.close();
    }
}
