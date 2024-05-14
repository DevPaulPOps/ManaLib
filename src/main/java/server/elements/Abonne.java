package server.elements;

public class Abonne {
    private final int numero;
    private final String nom;
    private final String dateNaissance;

    public Abonne(int numero, String nom, String dateNaissance) {
        this.numero = numero;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
    }

    public int getNumero() {
        return this.numero;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getNom() {
        return nom;
    }
}
