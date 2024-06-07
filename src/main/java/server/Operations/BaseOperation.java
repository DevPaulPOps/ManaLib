package server.Operations;

import server.Exception.EmpruntException;
import server.Exception.ReservationException;
import server.Operations.Reservation.Reservation;
import server.db.model.AbonneModel;
import server.elements.Abonne;
import server.elements.Documents.Document;
import server.serv.MediathequeService;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public abstract class BaseOperation extends MediathequeService {

    public BaseOperation(Socket socket) {
        super(socket);
    }

    protected int getNumero(String message) throws IOException {
        getBttpProtocole().sendResponse(message);
        return Integer.parseInt(getBttpProtocole().getResponse());
    }

    public String tryOperation(Abonne abonne, Document document) {
        try {
            document.reservation(abonne);
            return "Le document a été " + getOperation() + " avec succès.";
        } catch (EmpruntException | ReservationException e) {
            return "Erreur lors " + getErreur() + e.getMessage();
        }
    }

    public Abonne getAbonne() throws IOException, SQLException {
        return new AbonneModel<>().getById(getNumero("Votre numéro de client : "));
    }

    public Document getDocument() throws IOException {
        return listCatalogue.get(getNumero("Le numéro du document"));
    }

    public abstract String getOperation();
    public abstract String getErreur();
}
