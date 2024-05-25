package server.elements.Documents;

public class Livre extends Document {
    public Livre(int numero, String titre, String state, Integer abonneId, boolean contenuAdulte) {
        super(numero, titre, state, abonneId, contenuAdulte);
    }
}
