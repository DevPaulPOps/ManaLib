package server.Operations.Emprunt;

import server.db.model.AbonneModel;
import server.elements.Abonne;
import server.elements.Documents.Document;
import server.elements.interfaces.Documents;
import server.serv.MediathequeService;

import java.net.Socket;
import java.sql.SQLException;

public class empruntService extends MediathequeService {

    public empruntService(Socket socket) {
        super(socket);
    }

    // TODO
    @Override
    public void lancement() {

    }

    public void tryEmprunt(int numClient , int numDoc) throws SQLException {
        Document document = listCatalogue.get(numDoc);
        Abonne abonne = new AbonneModel<>().getById(numClient);

        document.emprunt(abonne);
    }
}
