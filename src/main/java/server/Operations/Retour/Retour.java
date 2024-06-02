package server.Operations.Retour;

import server.Operations.Emprunt.Emprunt;
import server.Operations.Reservation.Reservation;
import server.elements.interfaces.Documents;

public class Retour {

    public static void retourner(Documents documents) {
        if (Emprunt.estEmprunte(documents))
            Emprunt.cancelEmprunt(documents);
    }

    public static boolean estRetourner(Documents documents) {
        return !Emprunt.estEmprunte(documents) && !Reservation.estReserve(documents);
    }
}
