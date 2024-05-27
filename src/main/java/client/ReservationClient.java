package client;

import server.db.model.AbonneModel;
import server.db.model.DocumentModel;
import server.elements.Abonne;
import server.elements.Documents.Document;
import server.serv.MediathequeServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

public class ReservationClient {
    public void launch(MediathequeServer server) {
        try {
            //Clavier utilisateur
            BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
            server.run();

            BufferedReader sin = new BufferedReader (new InputStreamReader(server.getServerSocket().accept().getInputStream()));
            PrintWriter sout = new PrintWriter(server.getServerSocket().accept().getOutputStream ( ), true);

            //Premiere ligne envoye par le serveur
            String line;
            line = sin.readLine();
            System.out.println(line);
            System.out.println(sin.readLine());

            //Demande au user
            String numAbonne = clavier.readLine();

            //Envoie au serveur
            sout.println(numAbonne);

            System.out.println(sin.readLine());

            //Deuxieme demande au user
            sout.println(clavier.readLine());

            //Resultat
            System.out.println(sin.readLine());


        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    //Error le probleme avec le save
    public void insertDataTmp() throws SQLException {
        DocumentModel doc = new DocumentModel();
        AbonneModel abonneModel = new AbonneModel();

        Abonne abonne = new Abonne(1, "Paul", new Date(27 / 2004));

        abonneModel.save(abonne);

        Document docu = new Document(1, "YO", "disponible", 1);
        doc.save(docu);
    }
}
