package server.elements;

import server.Exception.EmpruntException;
import server.Exception.ReservationException;
import server.Exception.RetourException;
import server.elements.interfaces.Abonnes;
import server.elements.interfaces.Documents;

public class Document implements Documents {

    /**
     * @return
     */
    @Override
    public int numero() {
        return 0;
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
}
