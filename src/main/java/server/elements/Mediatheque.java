package server.elements;

import server.elements.Documents.Document;
import java.util.ArrayList;
import java.util.HashMap;

//Voir si on met des ID ou l'objet =>

//Lors de la réservation ou de l’emprunt, on doit préciser ces 2 numéros. Lors du retour, le numéro de
//DVD suffit (vous pouvez ramener un DVD trouvé dans la rue par exemple ou pour le compte de
//quelqu’un d’autre). Pour simplifier, on suppose que ces numéros sont connus de l’abonné qui
//réserve/emprunte/rend un document et peuvent donc être simplement saisis au clavier.
public class Mediatheque {
    private ArrayList<Abonne> listesAbonnes;
    private ArrayList<Document> listesDocuments;
    private HashMap<Document, Abonne> listeReservEmprunt;

    public Mediatheque() {
        listesAbonnes = new ArrayList<>();
        listesDocuments = new ArrayList<>();
        listeReservEmprunt = new HashMap<>();
    }

    public Mediatheque(ArrayList<Abonne> listesAbonnes, ArrayList<Document> listesDocuments) {
        this.listesAbonnes = listesAbonnes;
        this.listesDocuments = listesDocuments;
        listeReservEmprunt = new HashMap<>();
    }

    //Un abonne peut emprunte si pas reserve
    //Un abonne peut emprunte si reserve
    public void setReservEmprunt(Document document, Abonne abonne) {
        if (estPasDansMediatheque(document,abonne))
            return;

        if (dejaReserve(document)) {
            document.reservation(abonne);
            listeReservEmprunt.put(document, abonne);
        }
    }

    //On peut un ramener un DVD au pif ? Qui ne fait pas partie de la mediatheque
    //Lors de la réservation ou de l’emprunt, on doit préciser ces 2 numéros. Lors du retour, le numéro de
    //DVD suffit (vous pouvez ramener un DVD trouvé dans la rue par exemple ou pour le compte de
    //quelqu’un d’autre)
    public void retourDocument(int documentId) {
//        if (estUnDocument(document))
//            return;

//        if (listeReservEmprunt.)
    }

    private boolean estPasDansMediatheque(Document document, Abonne abonne) {
        return !estUnAbonne(abonne) && !estUnDocument(document);
    }

    private boolean dejaReserve(Document document) {
        return (document.estRetourne() || document.estReserve());
    }


    public boolean estUnAbonne(Abonne abonne) {
        assert (abonne != null);
        return listesAbonnes.contains(abonne);
    }

    public boolean estUnDocument(Document document) {
        assert (document != null);
        return listesDocuments.contains(document);
    }
}
