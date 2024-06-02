package server.Operations.Reservation;

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
import java.util.List;

import static java.lang.Integer.parseInt;


/*
    le service de réservation lui
    envoie le catalogue et lui demande son numéro d’abonné et le numéro de document qu’il souhaite
    réserver.
*/


// TODO
//Essayer de regler le probleme : quand on saute une ligne, on doit redemander partie client de faire un readline.
//Ducoup l'affichage moche.
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
            sout.println("Bienvenue sur le service de reservation, voici le catalogue : " + catalogue);

            sout.print("Votre numero de client : ");
            String stringAboId = sin.readLine();
            int numberAboId = Integer.parseInt(stringAboId);

            sout.print("Le numero du document : ");
            String stringDocId = sin.readLine();
            int numberDocId = parseInt(stringDocId);

            // Voir si l'abonne existe ?
            // Voir si le document existe ?
            // TODO

            tryReserve(numberAboId,numberDocId);

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

    public String showCatalogue() {
        this.listCatalogue = ManageDataStorage.getOnlyDocumentDataStorage();

        StringBuilder sb = new StringBuilder();

        for (var document : listCatalogue) {
            sb.append(document.toString()).append(System.lineSeparator());
        }

        return sb.toString();
    }

    public void tryReserve(int numClient, int numDoc) throws SQLException {
        Document document = listCatalogue.get(numDoc);

        if (document == null) {
            return;
        }

        Abonne abonne = new AbonneModel<>().getById(numClient);
        document.reservation(abonne);
    }
}
