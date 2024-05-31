package server.Operations.Reservation;

import server.Exception.EmpruntException;
import server.Exception.ReservationException;
import server.Operations.Emprunt.Emprunt;
import server.elements.interfaces.Abonnes;

import java.util.Timer;

public class Reservation {
    private static Timer timer;
    private static Abonnes abonne;

    public Reservation() {
        timer = new Timer();
        abonne = null;
    }

    public static void reserver(Abonnes abonneI) {

        if (estReservePar(abonneI)) {
            setAbonne(abonneI);
            return;
        }

        if (estReserve()) {
            throw new ReservationException();
        } else if (Emprunt.estEmprunte()) {
            throw new EmpruntException();
        } else {
            setAbonne(abonneI);
            startReservationDelay();
        }
    }

    public static boolean estReserve() {
        return timer != null;
    }

    private static void setAbonne(Abonnes abonneI) {
        abonne = abonneI;
    }

    public static boolean estReservePar(Abonnes abonnesI) {
        return abonnesI.getIdAbonne() == abonnesI.getIdAbonne();
    }

    public static void cancelReservation() {
        timer.cancel();
        abonne = null;
    }

    public static void startReservationDelay() {
//        timer.schedule(new AnnulationReservationTask(this), AnnulationReservationTask.getDelay());
    }
}