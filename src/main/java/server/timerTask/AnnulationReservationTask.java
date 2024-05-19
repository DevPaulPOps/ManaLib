package server.timerTask;

import server.Operations.Reservation.Reservation;

import java.util.TimerTask;

public class AnnulationReservationTask extends TimerTask {
    private final Reservation reservation;

    public AnnulationReservationTask(Reservation reservation) {
        this.reservation = reservation;
    }

    public static long getDelay() {
        // 1h30
        return 90 * 60 * 1000;
    }

    @Override
    public void run() {
        reservation.cancelReservation();
    }
}