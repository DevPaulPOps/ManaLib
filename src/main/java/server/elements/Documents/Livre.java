package server.elements.Documents;

public class Livre extends Document {
    // Constructeur pour creer un livre
    public Livre(String titre, String state, Integer abonneId) {
        super(titre, state, abonneId);
    }

    // Constructeur pour mettre a jour un DVd
    public Livre(int idLivre, String titre, String state, Integer abonneId) {
        super(idLivre, titre, state, abonneId);
    }
}
