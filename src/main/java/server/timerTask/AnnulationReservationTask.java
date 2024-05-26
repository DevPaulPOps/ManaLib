package server.timerTask;

import server.Operations.Reservation.Reservation;

import java.util.TimerTask;

public class AnnulationReservationTask extends TimerTask {
    private final Reservation reservation;

    public AnnulationReservationTask(Reservation reservation) {
        this.reservation = reservation;
    }

    // 1h30
    public static long getDelay() {
        return 90 * 60 * 1000;
    }

    @Override
    public void run() {
        Reservation.cancelReservation();
    }
}