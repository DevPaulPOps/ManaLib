package server.timerTask;

import server.Operations.Reservation.Reservation;
import server.elements.interfaces.Documents;
import server.utils.Utils;

import java.util.TimerTask;

public class AnnulationReservationTask extends TimerTask {
    private static final long DELAY = 90 * 60 * 1000; // 1h30 in milliseconds
    private final Documents document;

    public AnnulationReservationTask(Documents documents) {
        this.document = documents;
    }

    public static long getDelay() {
        return DELAY;
    }

    @Override
    public void run() {
        Reservation.cancelReservation(document);

        //Envoie de l'email par defaut
        Utils.setDefaultMail(document);
    }
}