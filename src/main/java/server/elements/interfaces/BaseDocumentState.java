package server.elements.interfaces;

public interface BaseDocumentState {
    public boolean estRetourne();
    public boolean estEmprunte();
    public boolean estReserve();
    public void changeState(StateDocument stateDocument);
}
