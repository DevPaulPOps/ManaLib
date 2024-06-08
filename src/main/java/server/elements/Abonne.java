package server.elements;

import server.db.model.AbonneModel;
import server.elements.interfaces.Abonnes;

import java.sql.SQLException;
import java.util.Date;

public class Abonne implements Abonnes {

    private final String nom;
    private final Date dateNaissance;

    //Crée automatiquement depuis la base de donnee
    private Integer idAbonne;

    //constructeur pour creer un abonne
    public Abonne(String nom, Date dateNaissance) {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
    }

    //constructeur pour mettrea jour un abonne existant avec l'id
    public Abonne(int idAbonne, String nom, Date dateNaissance) {
        this(nom, dateNaissance);
        this.idAbonne = idAbonne;
    }

    /**
     * @return
     */
    @Override
    public int getIdAbonne() {
        return this.idAbonne;
    }

    @Override
    public Integer getEntityId() {
        return idAbonne;
    }

    /**
     * @return
     */
    @Override
    public Date getDateDeNaissance() {
        return this.dateNaissance;
    }

    public String getNom() {
        return this.nom;
    }

    /**
     * Methode pour sauvegarder les données present dans la base de données vers l'application
     *
     * @throws SQLException
     */
    @Override
    public void saveFromDB() throws SQLException {
        AbonneModel<Abonne> abonneModelToSave = new AbonneModel<>();
        abonneModelToSave.save(this);
    }

    @Override
    public String toString() {
        return idAbonne + "  " + nom + "  " + dateNaissance;
    }
}