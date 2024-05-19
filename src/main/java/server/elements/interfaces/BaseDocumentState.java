package server.elements.interfaces;

public interface BaseDocumentState {
    boolean estRetourne();

    boolean estEmprunte();

    boolean estReserve();

    void changeState(StateDocument stateDocument);
}
