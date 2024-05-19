package server.elements.interfaces;

import server.elements.Documents.Document;

public interface StateDocument {
    boolean estRetourne();

    boolean estEmprunte();

    boolean estReserve();

    void reserver(Document document);

    void emprunter(Document document);

    void retourner(Document document);
}
