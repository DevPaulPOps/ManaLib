package server.serv;

import server.db.data.ManageDataStorage;
import server.elements.Documents.Document;
import server.serv.bttp.BttpProtocole;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public abstract class MediathequeService implements Runnable {
    private final Socket socket;
    private final BttpProtocole bttp;
    protected List<Document> listCatalogue;

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
        this.bttp.initInOut();
        try {
            lancement();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initCatalogue() {
        this.listCatalogue = ManageDataStorage.getOnlyDocumentDataStorage();
    }

    public void start() {
        new Thread(this).start();
    }

    abstract public void lancement() throws IOException;
}
