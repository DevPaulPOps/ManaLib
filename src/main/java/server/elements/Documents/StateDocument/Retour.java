package server.elements.Documents.StateDocument;

import server.Exception.RetourException;
import server.elements.Documents.Document;
import server.elements.interfaces.StateDocument;

public class Retour implements StateDocument {

    @Override
    public boolean estRetourne() {
        return true;
    }

    @Override
    public boolean estEmprunte() {
        return false;
    }

    @Override
    public boolean estReserve() {
        return false;
    }

    @Override
    public void retourner(Document document) throws RetourException {
        throw new RetourException();
    }

    @Override
    public void emprunter(Document document) {
        document.changeState(new Emprunte());
    }

    @Override
    public void reserver(Document document) {
        document.changeState(new Reserve());
    }
}
