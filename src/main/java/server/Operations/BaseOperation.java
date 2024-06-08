package server.Operations;

import server.Exception.EmpruntException;
import server.Exception.ReservationException;
import server.Operations.Reservation.Reservation;
import server.db.MediathequeDbService;
import server.db.data.ManageDataStorage;
import server.db.model.AbonneModel;
import server.elements.Abonne;
import server.elements.Documents.Document;
import server.elements.interfaces.Abonnes;
import server.elements.interfaces.Documents;
import server.serv.MediathequeService;
import server.utils.Utils;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public abstract class BaseOperation extends MediathequeService {

    public BaseOperation(Socket socket) {
        super(socket);
    }

    protected int getNumero(String message) throws IOException {
        getBttpProtocole().sendResponse(message);
        return Integer.parseInt(getBttpProtocole().getResponse());
    }

    public String tryOperation(Abonnes abonne, Documents document) throws IOException {
        try {
            setOperation(abonne, document);
            ManageDataStorage.addDataStorage(document); //Mettre a jour le stockage lors du catalogue
            return "Le document a été " + getOperation() + " avec succès";
        } catch (EmpruntException e) {
            return "Erreur lors " + getErreur() + e.getMessage();
        } catch (ReservationException e) {
            return handleReservationException(document, abonne);
        }
    }

    private String handleReservationException(Documents document, Abonnes abonne) throws IOException {
        getBttpProtocole().sendResponse("Ce document est déjà réservé. Voulez-vous être notifié lorsque le document sera disponible ? (oui / non) : ");
        String reponse = getBttpProtocole().getResponse();

        if ("oui".equalsIgnoreCase(reponse)) {
            return handleNotificationRequest(document, abonne);
        } else if ("non".equalsIgnoreCase(reponse)) {
            return "Vous avez refusé d'être notifié.";
        } else {
            return "Erreur lors de la confirmation.";
        }
    }

    private String handleNotificationRequest(Documents document, Abonnes abonne) {
        try {
            mail(document, abonne);
            return "Email enregistrée avec succès.";
        } catch (Exception e) {
            return "Erreur lors " + getErreur() + e.getMessage();
        }
    }

    //Voir si ducoup on creer un mail pour les utilisateur qui sera un de base
    private void mail(Documents documents, Abonnes abonnes) throws IOException {
//        getBttpProtocole().sendResponse("Votre adresse mail : ");
//        String abonneMail = getBttpProtocole().getResponse();

        if (Utils.setDefaultMail(documents)) {
            Reservation.ajoutFileAttente(documents, abonnes);
        }
    }

    public Abonne getAbonne() throws IOException, SQLException {
        return new AbonneModel<>().getById(getNumero("Votre numéro de client : "));
    }

    public Document getDocument() throws IOException {
        return findDocumentById(getNumero("Le numéro du document"));
    }

    private Document findDocumentById(int id) {
        for (Document document : listCatalogue) {
            if (document.getEntityId() == id) {
                return document;
            }
        }
        return null;
    }

    public abstract String getOperation();
    public abstract String getErreur();
    public abstract void setOperation(Abonnes abonne, Documents documents);
}
