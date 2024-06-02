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
    private final static HashMap<Documents, HashMap<Abonnes, Timer>> lstReserve = new HashMap<>();

    public static void reserver(Documents document, Abonnes abonneI) throws ReservationException, EmpruntException {
        synchronized (lstReserve) {
            if (estReservePar(document, abonneI)) {
                return;
            }

            if (estReserve(document)) {
                throw new ReservationException();
            } else if (Emprunt.estEmprunte(document)) {
                throw new EmpruntException();
            }

            startReservationDelay(document, abonneI);
        }
        // Mettre à jour le state du document en dehors de la section synchronisée pour éviter les verrouillages prolongés
    }

    public static synchronized boolean estReserve(Documents document) {
        return lstReserve.containsKey(document);
    }

    public static synchronized boolean estReservePar(Documents document, Abonnes abonneI) {
        return lstReserve.containsKey(document) && lstReserve.get(document).containsKey(abonneI);
    }

    public static synchronized void cancelReservation(Documents document) {
        lstReserve.remove(document);
    }

    public static void startReservationDelay(Documents document, Abonnes abonneI) {
        Timer timer = new Timer();
        timer.schedule(new AnnulationReservationTask(document), AnnulationReservationTask.getDelay());

        synchronized (lstReserve) {
            lstReserve.computeIfAbsent(document, k -> new HashMap<>()).put(abonneI, timer);
        }
    }
}