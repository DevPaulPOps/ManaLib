package server.Operations.Retour;

import server.Operations.Emprunt.Emprunt;
import server.Operations.Reservation.Reservation;
import server.elements.interfaces.Abonnes;

public class Retour {

    public static void retourner() {
        if (Emprunt.estEmprunte())
            Emprunt.setAbonnes(null);
    }

    public static boolean estRetourner() {
        return !Emprunt.estEmprunte() && !Reservation.estReserve();
    }
}
