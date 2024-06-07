package server.Operations.Emprunt;

import server.Operations.BaseOperation;
import server.elements.Abonne;
import server.elements.Documents.Document;
import server.stateConstante.StateConstante;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class empruntService extends BaseOperation {

    public empruntService(Socket socket) {
        super(socket);
    }

    @Override
    public void lancement() {
        try {
            getBttpProtocole().sendResponse("Bienvenue sur le service d'emprunt.");

            Abonne abonne = getAbonne();
            Document document = getDocument();

            if (abonne == null || document == null) {
                getBttpProtocole().sendResponse("Abonné ou document introuvable.");
            } else {
                getBttpProtocole().sendResponse(tryOperation(abonne, document));
            }

        } catch (IOException | SQLException e) {
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

    public String getOperation() {
        return StateConstante.EMPRUNTE;
    }

    public String getErreur() {
        return "de l'emprunt : ";
    }
}
