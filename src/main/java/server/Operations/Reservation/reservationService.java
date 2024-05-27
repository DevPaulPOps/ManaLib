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
        try {
            BufferedReader sin = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
            PrintWriter sout = new PrintWriter(getSocket().getOutputStream(), true);
            sout.println("Vous êtes sur le service de reservation.\n"+ "Voici le catalogue : \n" + showCatalogue());

            sout.println("Votre numero de client : ");
            String number = sin.readLine();

//            BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));

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

    public String showCatalogue() throws SQLException {
        ArrayList<Document> catalogue = ManageDataStorage.getOnlyDocumentDataStorage();

        StringBuilder sb = new StringBuilder();

        catalogue.forEach(document -> sb.append(document + "\n"));

        return sb.toString();
    }
}
