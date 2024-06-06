package server.Operations.Retour;

import server.elements.Documents.Document;
import server.serv.MediathequeService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class retourService extends MediathequeService {

    public retourService(Socket socket) {
        super(socket);
        initCatalogue();
    }

    @Override
    public void lancement() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
            PrintWriter out = new PrintWriter(getSocket().getOutputStream(), true);
            out.println("Vous êtes sur le service de retour.\nLe document appartient-il a quelqu'un ? (oui / non) : ");

            String docTrouve = in.readLine();
            while (true) {
                if ("oui".equalsIgnoreCase(docTrouve)) {
                    out.println("Numéro du document : ");
                    String numeroDocument = in.readLine();
                    int numeroDoc = Integer.parseInt(numeroDocument);

                    tryRetour(numeroDoc);
                    break;
                } else if ("non".equalsIgnoreCase(docTrouve)) {
                    out.println("Numéro du document : ");
                    String numeroDocument = in.readLine();
                    int numeroDoc = Integer.parseInt(numeroDocument);

                    // Stocker le numéro de document si nécessaire
                    break;
                } else {
                    out.println("Vous devez répondre par oui ou non.");
                    docTrouve = in.readLine();
                }
            }

            out.println("Le document a bien été remis avec succès.");

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

    public void tryRetour(int numeroDoc) {
        Document document = listCatalogue.get(numeroDoc);

        if (document != null) {
            document.retour();
        }
    }
}
