package server.Exception;

public class NotAdultException extends RuntimeException {
    public NotAdultException() {
        super("Vous n'êtes pas majeur, vous ne pouvez pas emprunter ce DVD.");
    }
}
