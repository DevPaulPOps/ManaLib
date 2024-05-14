package server.elements.Documents.StateDocument;

import server.elements.Documents.Document;
import server.Exception.ReservationException;
import server.elements.interfaces.StateDocument;

public class Reserve implements StateDocument {

    @Override
    public boolean estRetourne() {
        return false;
    }

    @Override
    public boolean estEmprunte() {
        return false;
    }

    @Override
    public boolean estReserve() {
        return true;
    }

    // l’abonné doit passer à la médiathèque dans les 1h30 l’emprunter (sinon la réservation est annulée).
    @Override
    public void retourner(Document document) {
        document.changeState(new Retour());
    }

    @Override
    public void emprunter(Document document ) {
        document.changeState(new Emprunte());
    }

    @Override
    public void reserver(Document document ) {
        throw new ReservationException();
    }
}
