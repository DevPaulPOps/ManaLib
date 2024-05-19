package server.Operations.Reservation;

import server.timerTask.AnnulationReservationTask;

import java.util.Timer;

public class Reservation {
    private final Timer timer;

    public Reservation() {
        this.timer = new Timer();
    }

    public void startReservationDelay() {
        timer.schedule(new AnnulationReservationTask(this), AnnulationReservationTask.getDelay());
    }

    public void cancelReservation() {
        timer.cancel();
    }
}