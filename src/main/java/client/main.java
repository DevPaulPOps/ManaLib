package client;

import server.db.MediathequeDbService;
import server.db.data.ManageDataStorage;
import server.db.model.AbonneModel;
import server.db.model.DocumentModel;
import server.elements.Abonne;
import server.elements.Documents.Document;
import server.environment.Environment;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class main {
    public static void main(String[] args) throws IOException {

        SelectionPortServer.messageBienvenue();

        if (args.length != 1) {
            System.err.println("Services disponible : " + SelectionPortServer.getServices());
            return;
        }

        ClientServer client;
        try {
            tmpInsertData();
            client = new ClientServer(SelectionPortServer.getPort(args[0]));
            System.out.println(client.getBttpProtocole().getResponseServer());

            //A voir vu que ducoup on recuperer pas les reponses du serveur
            while (true) {
                client.getBttpProtocole().initInOut();
                client.getBttpProtocole().getResponseServer();
                client.getBttpProtocole().getResponseServer();

                String line = client.getClavierInput().readLine();

                if (line.toLowerCase() == "exit") {
                    break;
                }

                System.out.println(client.getBttpProtocole().communicate(line));
            }
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void tmpInsertData() throws SQLException, ClassNotFoundException {
        MediathequeDbService.setJdbcClassDriver(Environment.DRIVER);
        MediathequeDbService.setJdbcUrl(Environment.URL);
        ManageDataStorage.initDataStorage();

//        String pattern = "MM/dd/yyyy";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//        Date date = new Date("03-12-2024");

        try {
            Abonne abonne = new Abonne(4,"Paul", new Date());
            AbonneModel abonneModel = new AbonneModel();
            abonneModel.save(abonne);
        } catch (Exception e) {

        }



//        Document doc = new Document(1,"TEST","libre", 1);
//        DocumentModel documentModel = new DocumentModel();
//        documentModel.save(doc);
    }
}
