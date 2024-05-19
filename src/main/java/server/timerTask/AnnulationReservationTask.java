package server.timerTask;

import java.util.TimerTask;
import server.Operations.Reservation.Reservation;

public class AnnulationReservationTask extends TimerTask {
    private final Reservation reservation;

    public AnnulationReservationTask(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public void run() {
        reservation.cancelReservation();
    }

    public static long getDelay() {
        // 1h30
        return 90 * 60 * 1000;
    }
}