package server.elements;

import server.elements.interfaces.Abonnes;

import java.util.Date;

public class Abonne implements Abonnes {

    private int idAbonne;
    private final String nom;
    private final Date dateNaissance;
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
}