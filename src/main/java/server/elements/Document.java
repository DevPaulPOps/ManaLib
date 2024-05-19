package server.elements;

import server.Exception.EmpruntException;
import server.Exception.ReservationException;
import server.Exception.RetourException;
import server.elements.interfaces.Abonnes;
import server.elements.interfaces.Documents;

public class Document implements Documents {

    private int numero;
    private String titre;
    private String state;
    private Integer idReserveur;

    public Document(int numero, String titre, String state, Integer idReserveur) {
        this.numero = numero;
        this.titre = titre;
        this.state = state;
        this.idReserveur = idReserveur;
    }

    public String getState() {
        return state;
    }

    public Integer getIdReserveur() {
        return idReserveur;
    }
    /**
     * @return
     */
    @Override
    public int numero() {
        return numero;
    }

    /**
     * @param ab
     * @pre ni réservé ni emprunté
     */
    @Override
    public void reservation(Abonnes ab) throws ReservationException {
        if (isReserved || isBorrowed) {
            throw new ReservationException();
        }
        reserverLeDocument(ab);
    }

    /**
     * @param ab
     * @pre libre ou réservé par l’abonné qui vient emprunter
     */
    @Override
    public void emprunt(Abonnes ab) throws EmpruntException {
        if (!isReserved && !isBorrowed) {
            throw new EmpruntException();
        }
        emprunterLeDocument(ab);
    }

    /**
     * @brief retour d’un document ou annulation d‘une réservation
     */
    @Override
    public void retour() throws RetourException {
        if (!isBorrowed) {
            throw new RetourException();
        }
        retournerLeDocument();
    }

    /**
     * @return
     */
    @Override
    public Integer getId() {
        return 0;
    }
}
