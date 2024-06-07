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

    private final String titre;
    private String state;
    private Integer abonneId;
    private Integer idDocument; //L'id du document est creer automatiquement dans la base de donnee.

    //Cosntructeur pour creer un document
    public Document(String titre, String state, Integer abonneId) {
        this.titre = titre;
        this.state = state;
        this.abonneId = abonneId;
    }

    //Constructeur pour mettre a jour un document existant avec l'id
    public Document(int idDocument, String titre, String state, Integer abonneId) {
        this(titre, state, abonneId);
        this.idDocument = idDocument;
    }

    public String getState() {
        return state;
    }

    public Integer getAbonneId() {
        return abonneId;
    }

    public void setAbonneId(Integer abonneId) {
        this.abonneId = abonneId;
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
        if (Reservation.estReserve(this) || Emprunt.estEmprunte(this)) {
            throw new ReservationException();
        }
        Reservation.reserver(this, ab);
    }

    /**
     * @param ab
     * @pre libre ou réservé par l’abonné qui vient emprunter
     */
    @Override
    public void emprunt(Abonnes ab) throws EmpruntException {
        if (!Reservation.estReservePar(this, ab) && !Emprunt.estEmprunte(this)) {
            throw new EmpruntException();
        }
        Reservation.reserver(this, ab);
    }

    /**
     * @brief retour d’un document ou annulation d‘une réservation
     */
    @Override
    public void retour() throws RetourException {
        if (!Emprunt.estEmprunte(this)) {
            throw new RetourException();
        }
        Retour.retourner(this);
    }

    /**
     * @return
     * @Override
     */
    public Integer getEntityId() {
        return idDocument;
    }

    /**
     * @throws SQLException Methode pour sauvegarder les données present dans la base de données vers l'application
     */
    @Override
    public void saveFromDB() throws SQLException {
        DocumentModel<Document> docModelToSave = new DocumentModel<>();
        docModelToSave.save(this);
    }

    @Override
    public String toString() {
        return "Document : " + idDocument +
                ", titre ='" + titre + '\'' +
                ", state ='" + state + '\'';
    }
}
