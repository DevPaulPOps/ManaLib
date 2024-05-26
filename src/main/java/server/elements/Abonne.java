package server.elements;

import server.db.model.AbonneModel;
import server.elements.interfaces.Abonnes;

import java.sql.SQLException;
import java.util.Date;

public class Abonne implements Abonnes {

    private final String nom;
    private final Date dateNaissance;
    private final int idAbonne;
    private Integer idStorage;

    public Abonne(int idAbonne, String nom, Date dateNaissance) {
        this.idAbonne = idAbonne;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
    }

    /**
     * @return
     */
    @Override
    public int getIdAbonne() {
        return this.idAbonne;
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
     * @return
     */
    @Override
    public Integer getIdStorage() {
        return this.idStorage;
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
}