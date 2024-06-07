package server.Operations.Retour;

import server.Exception.EmpruntException;
import server.Exception.RetourException;
import server.elements.Documents.Document;
import server.serv.MediathequeService;
import java.io.IOException;
import java.net.Socket;

public class retourService extends MediathequeService {

    public retourService(Socket socket) {
        super(socket);
    }

    @Override
    public void lancement() {
        try {
            getBttpProtocole().sendResponse("Vous êtes sur le service de retour.");
            getBttpProtocole().sendResponse("Le document appartient-il à quelqu'un ? (oui / non) : ");

            boolean appartient = "oui".equalsIgnoreCase(getBttpProtocole().getResponse());
            getBttpProtocole().sendResponse("Numéro du document : ");

            getBttpProtocole().sendResponse(tryRetour(Integer.parseInt(getBttpProtocole().getResponse().trim()), appartient));

        } catch (IOException e) {
            System.err.println("Erreur lors de la communication avec le client : " + e.getMessage());
        } finally {
            try {
                if (getSocket() != null) {
                    getSocket().close();
                }
            } catch (IOException e) {
                System.err.println("Erreur lors de la fermeture du socket : " + e.getMessage());
            }
        }
    }

    public String tryRetour(int numeroDoc, boolean appartient) {
        Document document = listCatalogue.get(numeroDoc);
        String messageRetourne = "Le document a été retourné avec succès.";
        String messageErreur = "Erreur lors du retour : ";

        try {
            if (!appartient) {
                Retour.retourner(document);
            } else {
                document.retour();
            }
            return messageRetourne;
        } catch (EmpruntException | RetourException e) {
            return messageErreur + e.getMessage();
        }
    }
}