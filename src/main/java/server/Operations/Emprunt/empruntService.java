package server.Operations.Emprunt;

import server.Exception.EmpruntException;
import server.db.model.AbonneModel;
import server.elements.Abonne;
import server.elements.Documents.Document;
import server.serv.MediathequeService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

public class empruntService extends MediathequeService {

    public empruntService(Socket socket) {
        super(socket);
    }

    @Override
    public void lancement() {
        try {
            BufferedReader sin = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
            PrintWriter sout = new PrintWriter(getSocket().getOutputStream(), true);
            sout.println("Bienvenue sur le service d'emprunt.");

            sout.print("Votre numéro de client : ");
            String stringAboId = sin.readLine();
            int numberAboId = Integer.parseInt(stringAboId);

            sout.print("Le numéro du document : ");
            String stringDocId = sin.readLine();
            int numberDocId = Integer.parseInt(stringDocId);

            // Vérification de l'existence de l'abonné et du document
            Abonne abonne = new AbonneModel<>().getById(numberAboId);
            Document document = listCatalogue.get(numberDocId);

            if (abonne != null && document != null) {
                tryEmprunt(abonne, document);
                sout.println("Le document a été emprunté avec succès.");
            } else {
                sout.println("Abonné ou document introuvable.");
            }

            sout.println("exit => pour quitter le service.");

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

    public void tryEmprunt(Abonne abonne, Document document) {
        try {
            document.emprunt(abonne);
        } catch (EmpruntException e) {
            System.err.println("Erreur lors de l'emprunt : " + e.getMessage());
        }
    }
}
