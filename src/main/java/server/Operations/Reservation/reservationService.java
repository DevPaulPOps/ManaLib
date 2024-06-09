package server.Operations.Reservation;

import server.Exception.ReservationException;
import server.Operations.BaseOperation;
import server.elements.interfaces.Abonnes;
import server.elements.interfaces.Documents;
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

            Abonnes abonne = getAbonne();
            Documents document = getDocument();

            if (abonne == null || document == null) {
                getBttpProtocole().sendResponse("Abonné ou document introuvable. End");
            } else {
                getBttpProtocole().sendResponse((tryOperation(abonne, document)) + " End");
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

        for (Documents document : listCatalogue) {
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

    @Override
    public void setOperation(Abonnes abonne, Documents documents) throws ReservationException {
        documents.reservation(abonne);
    }
}