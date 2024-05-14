package server.elements.interfaces;

import server.Exception.EmpruntException;
import server.Exception.ReservationException;
import server.Exception.RetourException;
import server.elements.Abonne;

public interface Documents {
    int numero();

    public void reservation(Abonne ab) throws ReservationException;
    public void emprunt(Abonne ab) throws EmpruntException;
    public void retour() throws RetourException;
}
