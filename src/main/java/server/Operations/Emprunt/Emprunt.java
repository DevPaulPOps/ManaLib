package server.Operations.Emprunt;

import server.Exception.EmpruntException;
import server.Operations.Reservation.Reservation;
import server.elements.interfaces.Abonnes;
import server.elements.interfaces.Documents;

import java.util.HashMap;

public class Emprunt {
    private static HashMap<Documents, Abonnes> lstEmprunts;

    public Emprunt() {
        lstEmprunts = new HashMap<>();
    }

    public Emprunt(Documents documents,Abonnes abonnesI) {
        this();
        lstEmprunts.put(documents, abonnesI);
    }

    public static boolean estEmprunte(Documents documents) {
        return lstEmprunts.containsKey(documents);
    }

    public static void cancelEmprunt(Documents documents) {
        lstEmprunts.remove(documents);
    }

    public static void emprunter(Documents documents, Abonnes abonnesI) {
        if (Reservation.estReservePar(documents, abonnesI)) {
            lstEmprunts.put(documents, abonnesI);
            Reservation.cancelReservation(documents);
        }
        else {
            throw new EmpruntException();
        }
    }
}
