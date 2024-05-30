package server.elements.Documents;

import server.Exception.NotAdultException;
import server.Exception.ReservationException;
import server.db.model.DVDModel;
import server.elements.interfaces.Abonnes;
import server.utils.Utils;

import java.sql.SQLException;

public class DVD extends Document {
    private boolean contenuAdulte;

    //Constructeur pour creer un DVD
    public DVD(String titre, String state, Integer abonneId, boolean contenuAdulte) {
        super(titre, state, abonneId);
        this.contenuAdulte = contenuAdulte;
    }

    // Constructeur pour mettre a jour un DVd
    public DVD(int idDvd, String titre, String state, Integer abonneId, boolean contenuAdulte) {
        super(idDvd, titre, state, abonneId);
        this.contenuAdulte = contenuAdulte;
    }

    @Override
    public void reservation(Abonnes ab) throws ReservationException, NotAdultException {
        if (Utils.isAdult(ab.getDateDeNaissance())) {
            throw new NotAdultException();
        }
        super.reservation(ab);
    }

    public boolean isContenuAdulte() {
        return contenuAdulte;
    }

    public void setContenuAdulte(boolean contenuAdulte) {
        this.contenuAdulte = contenuAdulte;
    }

    @Override
    public void saveFromDB() throws SQLException {
        DVDModel<DVD> dvdModelToSave = new DVDModel<>();
        dvdModelToSave.save(this);
    }

    @Override
    public String toString() {
        return super.toString() + ", isContenuAdulte = '" + contenuAdulte + '\'';
    }
}
