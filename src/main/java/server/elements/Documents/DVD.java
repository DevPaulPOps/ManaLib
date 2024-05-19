package server.elements.Documents;

public class DVD extends Document {
    private final boolean contenuAdulte;

    public DVD(int numero, String titre, boolean contenuAdulte) {
        super(numero, titre);
        this.contenuAdulte = contenuAdulte;
    }

    public boolean estContenuAdulte() {
        return contenuAdulte;
    }
}
