package server.Operations.Reservation;

import server.db.data.ManageDataStorage;
import server.elements.Documents.Document;
import server.serv.MediathequeService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;


/*
    le service de réservation lui
    envoie le catalogue et lui demande son numéro d’abonné et le numéro de document qu’il souhaite
    réserver.
*/

public class reservationService extends MediathequeService {

    public reservationService(Socket socket) {
        super(socket);
    }

    @Override
    public void lancement() throws IOException {
        try {
            BufferedReader sin = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
            PrintWriter sout = new PrintWriter(getSocket().getOutputStream(), true);
            sout.println("Bienvenue sur le service de reservation, voici le catalogue : \n\n" + showCatalogue() + "\n");

            sout.print("Votre numero de client : ");
            String stringAboId = sin.readLine();
            int numberAboId = Integer.parseInt(stringAboId);

            sout.print("Le numero du document : ");
            String stringDocId = sin.readLine();
            int numberDocId = parseInt(stringDocId);

            // Voir si l'abonne existe ?
            // Voir si le document existe ?
            // TODO

            sout.print("exit => pour quitter le service.");

        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (getSocket() != null) {
                getSocket().close();
            }
        } catch (IOException ignored) {
        }
    }

    public String showCatalogue() throws SQLException {
        ArrayList<Document> catalogue = ManageDataStorage.getOnlyDocumentDataStorage();

        StringBuilder sb = new StringBuilder();

        for (var document : catalogue) {
            sb.append(document.toString() + "\n");
        }

        return sb.toString();
    }

    public static String showCatalogue2() throws SQLException {
        ArrayList<Document> catalogue = ManageDataStorage.getOnlyDocumentDataStorage();

        StringBuilder sb = new StringBuilder();

        for (var document : catalogue) {
            sb.append(document.toString() + "\n");
        }

        return sb.toString();
    }
}
