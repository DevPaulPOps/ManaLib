package server.Operations.Emprunt;

import server.Exception.EmpruntException;
import server.Operations.Reservation.Reservation;
import server.elements.interfaces.Abonnes;
import server.elements.interfaces.Documents;
import java.util.HashMap;


public class Emprunt {
    private static final HashMap<Documents, Abonnes> lstEmprunts = new HashMap<>();

    public static synchronized boolean estEmprunte(Documents documents) {
        return lstEmprunts.containsKey(documents);
    }

    public static synchronized void cancelEmprunt(Documents documents) {
        lstEmprunts.remove(documents);
    }

    public static void emprunter(Documents documents, Abonnes abonnesI) {
        synchronized (lstEmprunts) {
            if (Reservation.estReservePar(documents, abonnesI)) {
                lstEmprunts.put(documents, abonnesI);
                Reservation.cancelReservation(documents);
            } else {
                throw new EmpruntException();
            }
        }
    }
}

