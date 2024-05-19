package server.elements;

import server.Exception.EmpruntException;
import server.Exception.NotAdultException;
import server.Exception.ReservationException;
import server.Exception.RetourException;
import server.elements.interfaces.Abonnes;
import server.elements.interfaces.DVDs;
import server.elements.interfaces.Documents;
import server.utils.Utils;

public class DVD implements DVDs {
    /**
     * @return
     */
    @Override
    public boolean isForAdults() {
        return false;
    }

    /**
     * @return
     */
    @Override
    public Documents getDocument() {
        return null;
    }

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
    public void reservation(Abonnes ab) throws ReservationException, NotAdultException {
        if (Utils.isAdult(ab.getDateOfBirth())) {
            throw new NotAdultException();
        }
        document.reservation(ab);
    }

    /**
     * @param ab
     * @pre libre ou réservé par l’abonné qui vient emprunter
     */
    @Override
    public void emprunt(Abonnes ab) throws EmpruntException {
        document.emprunt(ab);
    }

    /**
     * @brief retour d’un document ou annulation d‘une réservation
     */
    @Override
    public void retour() throws RetourException {
        document.retour();
    }

    /**
     * @return
     */
    @Override
    public String getId() {
        return 0;
    }
}
