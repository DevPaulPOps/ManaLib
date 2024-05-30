package server.elements.Documents;

import server.Exception.EmpruntException;
import server.Exception.ReservationException;
import server.Exception.RetourException;
import server.Operations.Emprunt.Emprunt;
import server.Operations.Reservation.Reservation;
import server.Operations.Retour.Retour;
import server.db.model.DocumentModel;
import server.elements.interfaces.Abonnes;
import server.elements.interfaces.Documents;

import java.sql.SQLException;

public class Document implements Documents {

    private final int idDocument;
    private final String titre;
    private final String state;
    private final Integer abonneId;
    private Integer idStorage;

    public Document(int idDocument, String titre, String state, Integer abonneId) {
        this.idDocument = idDocument;
        this.titre = titre;
        this.state = state;
        this.abonneId = abonneId;
    }

    public String getState() {
        return state;
    }

    public Integer getAbonneId() {
        return abonneId;
    }

    public String getTitre() {
        return titre;
    }


    @Override
    public int numero() {
        return this.idDocument;
    }

    /**
     * @param ab
     * @pre ni réservé ni emprunté
     */
    @Override
    public void reservation(Abonnes ab) throws ReservationException {
        if (Reservation.estReserve() || Emprunt.estEmprunte()) {
            throw new ReservationException();
        }
        Reservation.reserver(ab);
    }

    /**
     * @param ab
     * @pre libre ou réservé par l’abonné qui vient emprunter
     */
    @Override
    public void emprunt(Abonnes ab) throws EmpruntException {
        if (!Reservation.estReservePar(ab) && !Emprunt.estEmprunte()) {
            throw new EmpruntException();
        }
        Reservation.reserver(ab);
    }

    /**
     * @brief retour d’un document ou annulation d‘une réservation
     */
    @Override
    public void retour() throws RetourException {
        if (!Emprunt.estEmprunte()) {
            throw new RetourException();
        }
        Retour.retourner();
    }

    /**
     * @return
     */
    @Override
    public Integer getIdStorage() {
        return idStorage;
    }

    /**
     * @throws SQLException Methode pour sauvegarder les données present dans la base de données vers l'application
     */
    @Override
    public void saveFromDB() throws SQLException {
        DocumentModel<Document> docModelToSave = new DocumentModel<>();
        docModelToSave.save(this);
    }
}
