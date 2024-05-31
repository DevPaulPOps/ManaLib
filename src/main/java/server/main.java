package server;

import server.Operations.Emprunt.empruntServer;
import server.Operations.Reservation.reservationServer;
import server.Operations.Retour.retourServer;
import server.db.MediathequeDbService;
import server.db.data.DataFactory;
import server.db.model.AbonneModel;
import server.db.model.DVDModel;
import server.db.model.DocumentModel;
import server.db.model.Model;
import server.environment.Environment;
import server.serv.MediathequeServer;
import server.serv.MediathequeServerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        List<Class<? extends MediathequeServer>> serverClasses = Arrays.asList(
                empruntServer.class,
                reservationServer.class,
                retourServer.class
        );

        List<Class<? extends Model>> dataClasses = Arrays.asList(
                AbonneModel.class,
                DocumentModel.class,
                DVDModel.class
        );
        try {
            MediathequeDbService.setJdbcUrlClassDriver(Environment.URL, Environment.DRIVER);
//            tmpInsertData();
            DataFactory.createDataInAppToDb(dataClasses);
            MediathequeServerFactory.createMediathequeServer(serverClasses);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println(reservationService.showCatalogue2());
    }

//    public static void tmpInsertData() throws SQLException, ClassNotFoundException {
//        ManageDataStorage.initDataStorage();
//
//        Document doc = new Document("Mein Kampf","libre", 1);
//        DocumentModel documentModel = new DocumentModel();
//        documentModel.save(doc);
//
//        DVD dvd = new DVD("Jhonnyes", "busy", 1, true);
//        DVDModel dvdModel = new DVDModel();
//        dvdModel.save(dvd);
//    }
}
