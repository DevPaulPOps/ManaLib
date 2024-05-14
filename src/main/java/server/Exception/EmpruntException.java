package server.Exception;

public class EmpruntException extends RuntimeException {
    public EmpruntException() {
        super("Impossible d'effectuer l'emprunt de ce livre car il est deja emprunté ou déja reservé.");
    }
}
