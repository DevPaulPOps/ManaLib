package server.Operations.Emprunt;

import server.Operations.Reservation.Reservation;
import server.elements.interfaces.Abonnes;

public class Emprunt {
    static Abonnes abonnes;

    public Emprunt() {
        abonnes = null;
    }

    public Emprunt(Abonnes abonnesI) {
        abonnes = abonnesI;
    }

    public static boolean estEmprunte() {
        return abonnes != null;
    }

    public static void emprunter(Abonnes abonnesI) {
        if (abonnes == null || abonnes.getIdAbonne() == abonnesI.getIdAbonne()) {
            Reservation.cancelReservation();
            abonnes = abonnesI;
        }
    }

    public static void setAbonnes(Abonnes abonnesI) {
        abonnes = abonnesI;
    }

    public Abonnes getAbonne() {
        return abonnes;
    }
}
