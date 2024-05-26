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

public class reservationService extends MediathequeService {

    public reservationService(Socket socket) {
        super(socket);
    }

    @Override
    public void lancement() throws IOException {
        System.out.println("Lancement du service.\n");
        try {
            showCatalogue();
            BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader sin = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
            PrintWriter sout = new PrintWriter(getSocket().getOutputStream(), true);

            String line;
            line = sin.readLine();
            System.out.println(line);

            System.out.print(sin.readLine());
            String nbSub = clavier.readLine();
            sout.println(nbSub);
            System.out.print(sin.readLine());
            sout.println(clavier.readLine());

            System.out.print(sin.readLine());
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

    public void showCatalogue() throws SQLException {
        ArrayList<Document> catalogue = ManageDataStorage.getOnlyDocumentDataStorage();

        System.out.println("Voici les documents disponibles : \n");
        catalogue.forEach(document -> System.out.println(document));
    }
}
