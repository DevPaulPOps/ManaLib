package server;

import server.Operations.Emprunt.empruntServer;
import server.Operations.Reservation.reservationServer;
import server.Operations.Reservation.reservationService;
import server.Operations.Retour.retourServer;
import server.db.MediathequeDbService;
import server.db.data.DataFactory;
import server.db.data.ManageDataStorage;
import server.db.model.AbonneModel;
import server.db.model.DVDModel;
import server.db.model.DocumentModel;
import server.db.model.Model;
import server.elements.Abonne;
import server.elements.Documents.DVD;
import server.elements.Documents.Document;
import server.environment.Environment;
import server.serv.MediathequeServer;
import server.serv.MediathequeServerFactory;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        List<Class<? extends MediathequeServer>> serverClasses = new ArrayList<>();
        serverClasses.add(empruntServer.class);
        serverClasses.add(reservationServer.class);
        serverClasses.add(retourServer.class);

        List<Class<? extends Model>> dataClasses = new ArrayList<>();
        dataClasses.add(AbonneModel.class);
        dataClasses.add(DocumentModel.class);
        dataClasses.add(DVDModel.class);
        try {
            MediathequeDbService.setJdbcUrlClassDriver(Environment.URL, Environment.DRIVER);
            tmpInsertData();
            DataFactory.createDataInAppToDb(dataClasses);
            MediathequeServerFactory.createMediathequeServer(serverClasses);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(reservationService.showCatalogue2());
    }

    public static void tmpInsertData() throws SQLException, ClassNotFoundException {
        ManageDataStorage.initDataStorage();

//        try {
//            Abonne abonne = new Abonne("Paula", new java.sql.Date(new java.util.Date().getTime()));
//            AbonneModel abonneModel = new AbonneModel();
//            abonneModel.save(abonne);
//        } catch (Exception e) {
//
//        }
//
//        Document doc = new Document("Mein Kampf","libre", 1);
//        DocumentModel documentModel = new DocumentModel();
//        documentModel.save(doc);

        DVD dvd = new DVD(1,"Jhonnyes", "busy", 1, true);
        DVDModel dvdModel = new DVDModel();
        dvdModel.save(dvd);
    }
}
