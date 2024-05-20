package server.elements.interfaces;

import server.Exception.EmpruntException;
import server.Exception.ReservationException;
import server.Exception.RetourException;

public interface Documents extends DataStorage {
    int numero();

    /**
     * @pre ni réservé ni emprunté
     */
    void reservation(Abonnes ab) throws ReservationException;

    /**
     * @pre libre ou réservé par l’abonné qui vient emprunter
     */
    void emprunt(Abonnes ab) throws EmpruntException;

    /**
     * @brief retour d’un document ou annulation d‘une réservation
     */
    void retour() throws RetourException;
}