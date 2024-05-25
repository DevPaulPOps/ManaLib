package server.serv;

import server.serv.bttp.BttpProtocole;

import java.io.IOException;
import java.net.Socket;

public abstract class MediathequeService implements Runnable {
    private final Socket socket;
    private final BttpProtocole bttp;

    public MediathequeService(Socket socket) {
        this.socket = socket;
        this.bttp = new BttpProtocole(this.socket);
    }

    public BttpProtocole getBttpProtocole() {
        return bttp;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        this.bttp.communicate();
        try {
            lancement();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        new Thread(this).start();
    }

    abstract public void lancement() throws IOException;
}
