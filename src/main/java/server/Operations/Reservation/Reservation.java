package server.Operations.Reservation;

import server.Exception.EmpruntException;
import server.Exception.ReservationException;
import server.Operations.Emprunt.Emprunt;
import server.db.data.ManageDataStorage;
import server.elements.interfaces.Abonnes;
import server.elements.interfaces.Documents;
import server.timerTask.AnnulationReservationTask;

import java.util.*;

public class Reservation {
    private final static HashMap<String, HashMap<String, Timer>> lstReserve = new HashMap<>();
    private final static HashMap<Documents, Queue<Abonnes>> listeAttente = new HashMap<>();
    private final static ArrayList<Documents> lstDocuments = new ArrayList<>();

    public static void reserver(Documents document, Abonnes abonneI) throws ReservationException, EmpruntException {
        synchronized (lstReserve) {
            if (estReservePar(document, abonneI) || estReserve(document)) {
                throw new ReservationException();
            }
            if (Emprunt.estEmprunte(document)) {
                throw new EmpruntException();
            }
            startReservationDelay(document, abonneI);
        }
    }

    public static void ajoutFileAttente(Documents document, Abonnes abonneI) {
        synchronized (listeAttente) {

            Queue<Abonnes> queue = listeAttente.get(document);

            if (queue == null) {
                queue = new LinkedList<>();
            }

            queue.add(abonneI);
            listeAttente.put(document, queue);
        }
    }

    public static Abonnes getFirstAttente(Documents document) {
        synchronized (listeAttente) {
            if (!listeAttente.isEmpty()) {
                return listeAttente.get(document).peek();
            }
        }
        return null;
    }

    public static boolean estReserve(Documents document) {
        synchronized (lstReserve) {
            return lstReserve.containsKey(document.getEntityId().toString());
        }
    }

    public static boolean estReservePar(Documents document, Abonnes abonneI) {
        synchronized (lstReserve) {
            String id = document.getEntityId().toString();
            return lstReserve.containsKey(id) && lstReserve.get(id).containsKey(abonneI.getEntityId().toString());
        }
    }

    public static void cancelReservation(Documents document) {
        synchronized (lstReserve) {
            lstReserve.remove(document.getEntityId().toString());
            lstDocuments.remove(document);
        }
    }

    public static void startReservationDelay(Documents document, Abonnes abonneI) {
        Timer timer = new Timer();
        timer.schedule(new AnnulationReservationTask(document), AnnulationReservationTask.getDelay());

        synchronized (lstReserve) {
            lstReserve.computeIfAbsent(document.getEntityId().toString(), k -> new HashMap<>()).put(abonneI.getEntityId().toString(), timer);
            lstDocuments.add(document);
        }
    }

    public static void saveData() {
        synchronized (lstDocuments) {
            lstDocuments.forEach(ManageDataStorage::addDataStorage);
        }
    }
}