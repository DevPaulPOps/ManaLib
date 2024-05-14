package server.elements.Documents.StateDocument;

import server.Exception.EmpruntException;
import server.elements.Documents.Document;
import server.elements.interfaces.StateDocument;

public class Emprunte implements StateDocument {

    @Override
    public boolean estRetourne() {
        return false;
    }

    @Override
    public boolean estEmprunte() {
        return true;
    }

    @Override
    public boolean estReserve() {
        return false;
    }

    @Override
    public void retourner(Document document) {
        document.changeState(new Retour());
    }

    @Override
    public void emprunter(Document document) {
        throw new EmpruntException();
    }

    @Override
    public void reserver(Document document) {
        throw new EmpruntException();
    }
}
