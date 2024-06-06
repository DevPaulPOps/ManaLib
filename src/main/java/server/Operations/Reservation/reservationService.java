package server.Operations.Reservation;

import server.Exception.EmpruntException;
import server.Exception.ReservationException;
import server.db.data.ManageDataStorage;
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

public class reservationService extends MediathequeService {
    String catalogue;

    public reservationService(Socket socket) throws SQLException {
        super(socket);
        initCatalogue();
        catalogue = showCatalogue();
    }

    @Override
    public void lancement() throws IOException {
        try {
            BufferedReader sin = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
            PrintWriter sout = new PrintWriter(getSocket().getOutputStream(), true);
            sout.println("Bienvenue sur le service de réservation, voici le catalogue : " + catalogue);

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
                tryReserve(abonne, document);
                sout.println("Le document a été réservé avec succès.");
            } else {
                sout.println("Abonné ou document introuvable.");
            }

            sout.println("exit => pour quitter le service.");

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
        this.listCatalogue = ManageDataStorage.getOnlyDocumentDataStorage();
        StringBuilder sb = new StringBuilder();

        for (Document document : listCatalogue) {
            sb.append(document.toString()).append(System.lineSeparator());
        }

        return sb.toString();
    }

    public void tryReserve(Abonne abonne, Document document) {
        try {
            document.reservation(abonne);
        } catch (ReservationException | EmpruntException e) {
            System.err.println("Erreur lors de la réservation : " + e.getMessage());
        }
    }
}
