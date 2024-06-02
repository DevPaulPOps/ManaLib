package server.Operations.Reservation;

import server.Exception.EmpruntException;
import server.Exception.ReservationException;
import server.Operations.Emprunt.Emprunt;
import server.db.MediathequeDbService;
import server.db.model.DocumentModel;
import server.elements.Abonne;
import server.elements.Documents.Document;
import server.elements.interfaces.Abonnes;
import server.elements.interfaces.Documents;
import server.timerTask.AnnulationReservationTask;

import java.util.HashMap;
import java.util.Timer;

public class Reservation {
    private static HashMap<Documents, HashMap<Abonnes, Timer>> lstReserve;

    public Reservation() {
        lstReserve = new HashMap<>();
    }

    public static void reserver(Documents document, Abonnes abonneI) {

        if (estReservePar(document, abonneI)) {
            return;
        }

        if (estReserve(document)) {
            throw new ReservationException();
        } else if (Emprunt.estEmprunte(document)) {
            throw new EmpruntException();
        }

        startReservationDelay(document, abonneI);
        DocumentModel documentModel = new DocumentModel();

    }

    public static boolean estReserve(Documents document) {
        return lstReserve.containsKey(document);
    }

    public static boolean estReservePar(Documents documents, Abonnes abonnesI) {
        return lstReserve.get(documents).containsKey(abonnesI);
    }

    public static void cancelReservation(Documents document) {
        lstReserve.remove(document);
    }

    public static void startReservationDelay(Documents document, Abonnes abonneI) {
        Timer timer = new Timer();
        timer.schedule(new AnnulationReservationTask(document), AnnulationReservationTask.getDelay());
        lstReserve.computeIfAbsent(document, k -> new HashMap<>()).put(abonneI, timer);
    }
}