package server.elements.interfaces;

import server.Exception.EmpruntException;
import server.Exception.ReservationException;
import server.Exception.RetourException;
import server.elements.Abonne;

public interface Documents {
    int numero();

    void reservation(Abonne ab) throws ReservationException;

    void emprunt(Abonne ab) throws EmpruntException;

    void retour() throws RetourException;
}
