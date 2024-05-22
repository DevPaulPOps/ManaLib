package server.Operations.Reservation;

import server.elements.Abonne;
import server.elements.interfaces.Abonnes;
import server.timerTask.AnnulationReservationTask;

import java.util.Timer;

public class Reservation {
    private static Timer timer;
    private static Abonnes abonne;

    public Reservation() {
        this.timer = new Timer();
    }

    public void startReservationDelay() {
        timer.schedule(new AnnulationReservationTask(this), AnnulationReservationTask.getDelay());
    }

    public static void reserverLeDocument(Abonnes ab) {
        //startReservation ????
        abonne = ab;
    }

    public static boolean estReserve() {
        return timer != null;
    }

    public static boolean estEmprunte() {
        return abonne != null;
    }

    public static void emprunterLeDocument(Abonnes ab) {
        //startReservation ????
        abonne = ab;
    }

    public static void retournerLeDocument() {
        cancelReservation();
    }

    public static void cancelReservation() {
        timer.cancel();
    }
}