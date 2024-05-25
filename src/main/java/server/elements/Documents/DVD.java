package server.elements.Documents;

import server.Exception.NotAdultException;
import server.Exception.ReservationException;
import server.db.model.DVDModel;
import server.db.model.DocumentModel;
import server.elements.interfaces.Abonnes;
import server.utils.Utils;

import java.sql.SQLException;

public class DVD extends Document {

    public DVD(int numero, String titre, boolean contenuAdulte, String state, Integer abonneId) {
        super(numero, titre, state, abonneId, contenuAdulte);
    }

    @Override
    public void reservation(Abonnes ab) throws ReservationException, NotAdultException {
        if (Utils.isAdult(ab.getDateDeNaissance())) {
            throw new NotAdultException();
        }
        super.reservation(ab);
    }

    @Override
    public void saveFromDB() throws SQLException {
        DVDModel<DVD> dvdModelToSave = new DVDModel<>();
        dvdModelToSave.save(this);
    }
}
