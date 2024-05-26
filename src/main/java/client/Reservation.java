package client;

import server.Operations.Reservation.reservationServer;
import server.db.model.AbonneModel;
import server.db.model.DocumentModel;
import server.elements.Abonne;
import server.elements.Documents.Document;
import server.serv.MediathequeServer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class Reservation {
    public void launch(String host) {
        try {
            insertDataTmp();
            MediathequeServer server = new reservationServer();
            server.run();
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
