package server.elements.Documents;

import server.Exception.EmpruntException;
import server.Exception.ReservationException;
import server.Exception.RetourException;
import server.Operations.Reservation.Reservation;
import server.elements.interfaces.Abonnes;
import server.elements.interfaces.Documents;

public class Document implements Documents {

    private int idDocument;
    private String titre;
    private String state;
    private Integer abonneId;

    public Document(int idDocument, String titre, String state, Integer abonneId) {
        this.idDocument = idDocument;
        this.titre = titre;
        this.state = state;
        this.abonneId = abonneId;
    }

    public String getState() {
        return state;
    }

    public Integer getAbonneId() {
        return abonneId;
    }

    public String getTitre() {
        return titre;
    }

    @Override
    public int numero() {
        return this.idDocument;
    }

    /**
     * @param ab
     * @pre ni réservé ni emprunté
     */
    @Override
    public void reservation(Abonnes ab) throws ReservationException {
        if (Reservation.estReserve() || Reservation.estEmprunte()) {
            throw new ReservationException();
        }
        Reservation.reserverLeDocument(ab);
    }

    /**
     * @param ab
     * @pre libre ou réservé par l’abonné qui vient emprunter
     */
    @Override
    public void emprunt(Abonnes ab) throws EmpruntException {
        if (!Reservation.estReserve() && !Reservation.estEmprunte()) {
            throw new EmpruntException();
        }
        Reservation.emprunterLeDocument(ab);
    }

    /**
     * @brief retour d’un document ou annulation d‘une réservation
     */
    @Override
    public void retour() throws RetourException {
        if (!Reservation.estEmprunte()) {
            throw new RetourException();
        }
        Reservation.retournerLeDocument();
    }

    /**
     * @return
     */
    @Override
    public Integer getIdStorage() {
        return 0;
    }
}
