package server.elements.Documents;

import server.elements.Abonne;
import server.elements.Documents.StateDocument.Retour;
import server.Exception.EmpruntException;
import server.Exception.ReservationException;
import server.Exception.RetourException;
import server.elements.interfaces.BaseDocumentState;
import server.elements.interfaces.Documents;
import server.elements.interfaces.StateDocument;

public class Document implements Documents, BaseDocumentState {
    private StateDocument stateDocument;
    private final int numero;
    private final String titre;
    private int abonneId;

    public Document(int numero, String titre) {
        this.numero = numero;
        this.titre = titre;
        this.stateDocument = new Retour();
        this.abonneId = -1;
    }

    private void setAbonneId(int abonneId) {
        this.abonneId = abonneId;
    }

    @Override
    public boolean estRetourne() {
        return this.stateDocument.estRetourne();
    }

    @Override
    public boolean estEmprunte() {
        return this.stateDocument.estEmprunte() && abonneId != -1;
    }

    @Override
    public boolean estReserve() {
        return this.stateDocument.estReserve() && abonneId != -1;
    }

    @Override
    public int numero() {
        return this.numero;
    }

    /**
     * @param ab
     * @pre ni réservé ni emprunté
     */
    @Override
    public void reservation(Abonne ab) throws ReservationException {
        if (this.abonneId != -1 && ab.getNumero() != this.abonneId) {
            throw new ReservationException();
        }

        setAbonneId(ab.getNumero());
        this.stateDocument.reserver(this);
    }

    /**
     * @param ab
     * @pre libre ou réservé par l’abonné qui vient emprunter
     */
    @Override
    public void emprunt(Abonne ab) throws EmpruntException {
        if (this.abonneId != -1 && ab.getNumero() != this.abonneId) {
            throw new EmpruntException();
        }

        setAbonneId(ab.getNumero());
        this.stateDocument.emprunter(this);
    }

    /**
     * @brief retour d’un document ou annulation d‘une réservation
     */
    @Override
    public void retour() throws RetourException {
        setAbonneId(-1);
        this.stateDocument.retourner(this);
    }

    @Override
    public void changeState(StateDocument stateDocument) {
        this.stateDocument = stateDocument;
    }

    public String getTitre() {
        return titre;
    }

    public long getNumero() {
        return numero;
    }
}
