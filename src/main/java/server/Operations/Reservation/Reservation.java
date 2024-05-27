package server.Operations.Reservation;

import server.Exception.EmpruntException;
import server.Exception.ReservationException;
import server.elements.interfaces.Abonnes;
import server.timerTask.AnnulationReservationTask;

import java.util.Timer;

public class Reservation {
    private static Timer timer;
    private static Abonnes abonne;

    public Reservation() {
        timer = new Timer();
    }

    public void reserverLeDocument(Abonnes ab) {
        if (estReserve()) {
            throw new ReservationException();
        }
        else if (estEmprunte()) {
            throw new EmpruntException();
        }
        else {
            abonne = ab;
            startReservationDelay();
        }
    }

    public static boolean estReserve() {
        return timer != null;
    }

    public static boolean estEmprunte() {
        return abonne != null;
    }

    public static void emprunterLeDocument(Abonnes ab) {
        if (!estReserve() || ab.getIdAbonne() == abonne.getIdAbonne()) {
            abonne = ab;
            timer.cancel();
        } else {
            throw new EmpruntException();
        }
    }

    public static void retournerLeDocument() {
        cancelReservation();
    }

    public static void cancelReservation() {
        timer.cancel();
        abonne = null;
    }

    public void startReservationDelay() {
        timer.schedule(new AnnulationReservationTask(this), AnnulationReservationTask.getDelay());
    }
}