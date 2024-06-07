package server.Operations;

import jdk.jshell.execution.Util;
import server.Exception.EmpruntException;
import server.Exception.ReservationException;
import server.Operations.Reservation.Reservation;
import server.db.model.AbonneModel;
import server.elements.Abonne;
import server.elements.Documents.Document;
import server.elements.interfaces.Abonnes;
import server.elements.interfaces.Documents;
import server.serv.MediathequeService;
import server.utils.Utils;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Properties;

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
            document.reservation(abonne);
            return "Le document a été " + getOperation() + " avec succès.";
        } catch (EmpruntException e) {
            return "Erreur lors " + getErreur() + e.getMessage();
        } catch (ReservationException e) {

            getBttpProtocole().sendResponse("Ce document est deja reservé. Voulez-vous être notifié lorsque le document sera disponible ? (oui / non) : ");

            String reponse = getBttpProtocole().getResponse();

            if ("oui".equalsIgnoreCase(reponse)) {
                try {
                    mail(document, abonne);
                    return "Réussi";
                } catch (Exception f) {
                    return "Erreur lors " + getErreur() + f.getMessage();
                }
            }
            else if ("non".equalsIgnoreCase(reponse))
            {
                return "Vous avez refusé d'etre notifié.";
            } else
            {
                return "Erreur lors de la confirmation.";
            }
        }
    }

    private void mail(Documents documents, Abonnes abonnes) throws IOException {
//        getBttpProtocole().sendResponse("Votre adresse mail : ");
//        String abonneMail = getBttpProtocole().getResponse();

        if (Utils.setDefaultMail(documents, abonnes)) {
            Reservation.ajoutFileAttente(documents, abonnes);
        }
    }

    public Abonne getAbonne() throws IOException, SQLException {
        return new AbonneModel<>().getById(getNumero("Votre numéro de client : "));
    }

    public Document getDocument() throws IOException {
        return listCatalogue.get(getNumero("Le numéro du document"));
    }

    public abstract String getOperation();
    public abstract String getErreur();
}
