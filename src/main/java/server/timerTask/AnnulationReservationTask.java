package server.timerTask;

import server.Operations.Reservation.Reservation;
import server.elements.interfaces.Documents;

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
    }
}