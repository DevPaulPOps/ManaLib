package server.Exception;

public class ReservationException extends RuntimeException {
    public ReservationException() {
        super("Impossible de reservé le livre car il est deja reservé.");
    }
}
