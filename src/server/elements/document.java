package server.elements;

import server.Exception.EmpruntException;
import server.Exception.ReservationException;
import server.Exception.RetourException;
import server.elements.interfaces.Abonne;
import server.elements.interfaces.Document;

public class document implements Document {
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
    public void reservation(Abonne ab) throws ReservationException {

    }

    /**
     * @param ab
     * @pre libre ou réservé par l’abonné qui vient emprunter
     */
    @Override
    public void emprunt(Abonne ab) throws EmpruntException {

    }

    /**
     * @brief retour d’un document ou annulation d‘une réservation
     */
    @Override
    public void retour() throws RetourException {

    }
}
