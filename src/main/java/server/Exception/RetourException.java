package server.Exception;

public class RetourException extends RuntimeException {
    public RetourException() {
        super("Impossible de retourner le livre car il est déja disponible.");
    }
}
