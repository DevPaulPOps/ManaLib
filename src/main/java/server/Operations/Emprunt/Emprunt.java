package server.Operations.Emprunt;

import server.Exception.EmpruntException;
import server.Operations.Reservation.Reservation;
import server.db.data.ManageDataStorage;
import server.elements.interfaces.Abonnes;
import server.elements.interfaces.Documents;

import java.util.HashMap;


public class Emprunt {
    private static final HashMap<String, HashMap<Documents, Abonnes>> lstEmprunts = new HashMap<>();

    public static boolean estEmprunte(Documents documents) {
        synchronized (lstEmprunts) {
            String entityId = documents.getEntityId().toString();
            return lstEmprunts.containsKey(entityId);
        }
    }

    public static void cancelEmprunt(Documents documents) {
        synchronized (lstEmprunts) {
            String entityId = documents.getEntityId().toString();
            if (lstEmprunts.containsKey(entityId)) {
                lstEmprunts.get(entityId).remove(documents);
                if (lstEmprunts.get(entityId).isEmpty()) {
                    lstEmprunts.remove(entityId);
                }
            }
        }
    }

    public static void emprunter(Documents documents, Abonnes abonnesI) throws EmpruntException {
        synchronized (lstEmprunts) {
            String entityId = documents.getEntityId().toString();

            if (!Reservation.estReserve(documents) || Reservation.estReservePar(documents, abonnesI)) {
                lstEmprunts.computeIfAbsent(entityId, k -> new HashMap<>()).put(documents, abonnesI);
                if (Reservation.estReserve(documents)) {
                    Reservation.cancelReservation(documents);
                }
            } else {
                throw new EmpruntException();
            }
        }
    }

    public static void saveData() {
        synchronized (lstEmprunts) {
            lstEmprunts.forEach((entityId, documentMap) -> {
                documentMap.forEach((document, abonnes) -> {
                    ManageDataStorage.addDataStorage(document);
                });
            });
        }
    }
}

