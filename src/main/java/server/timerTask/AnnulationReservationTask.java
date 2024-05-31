package server.timerTask;

import server.Operations.Reservation.Reservation;

import java.util.TimerTask;

public class AnnulationReservationTask extends TimerTask {
    // 1h30 in milliseconds
    private static final long DELAY = 90 * 60 * 1000;
    private final Reservation reservation;

    public AnnulationReservationTask(Reservation reservation) {
        this.reservation = reservation;
    }

    public static long getDelay() {
        return DELAY;
    }

    @Override
    public void run() {
        Reservation.cancelReservation();
    }
}