package server.Operations.Reservation;

import server.Operations.BaseOperation;
import server.elements.Abonne;
import server.elements.Documents.Document;
import server.stateConstante.StateConstante;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class reservationService extends BaseOperation {

    public reservationService(Socket socket) {
        super(socket);
    }

    @Override
    public void lancement() {
        try {
            getBttpProtocole().sendResponse("Bienvenue sur le service de réservation, voici le catalogue : ");
            getBttpProtocole().sendResponse("\n" + showCatalogue());
            getBttpProtocole().sendResponse("FIN_DU_CATALOGUE");

            Abonne abonne = getAbonne();
            Document document = getDocument();

            if (abonne == null || document == null) {
                getBttpProtocole().sendResponse("Abonné ou document introuvable.");
            } else {
                getBttpProtocole().sendResponse((tryOperation(abonne, document) + " End."));
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de la communication avec le client : " + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException("Erreur SQL : " + e.getMessage(), e);
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

    public String showCatalogue() {
        StringBuilder sb = new StringBuilder();

        for (Document document : listCatalogue) {
            sb.append(document.toString()).append(System.lineSeparator());
        }

        return sb.toString();
    }

    public String getOperation() {
        return StateConstante.RESERVE;
    }

    public String getErreur() {
        return "de la reservation : ";
    }
}