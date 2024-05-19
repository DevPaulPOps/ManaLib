package server.elements;

import server.elements.interfaces.Abonnes;

import java.util.Date;

public class Abonne implements Abonnes {
    private final int numeroAbonne;
    private final String nom;
    private final Date dateNaissance;

    public Abonne(int numeroAbonne, String nom, Date dateNaissance) {
        this.numeroAbonne = numeroAbonne;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
    }

    /**
     * @return
     */
    @Override
    public int getNumberOfSubscribers() {
        return this.numeroAbonne;
    }

    /**
     * @return
     */
    @Override
    public Date getDateOfBirth() {
        return this.dateNaissance;
    }

    public String getNom() {
        return this.nom;
    }
}