package server.elements.Documents;

import server.Exception.NotAdultException;
import server.Exception.ReservationException;
import server.elements.interfaces.Abonnes;
import server.utils.Utils;

public class DVD extends Document {
    private final boolean contenuAdulte;

    public DVD(int numero, String titre, boolean contenuAdulte, String state, Integer abonneId) {
        super(numero, titre, state, abonneId);
        this.contenuAdulte = contenuAdulte;
    }

    public boolean estContenuAdulte() {
        return contenuAdulte;
    }

    @Override
    public void reservation(Abonnes ab) throws ReservationException, NotAdultException {
        if (Utils.isAdult(ab.getDateDeNaissance())) {
            throw new NotAdultException();
        }
        super.reservation(ab);
    }
}
