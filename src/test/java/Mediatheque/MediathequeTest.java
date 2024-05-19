/** package Mediatheque;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.Exception.ReservationException;
import server.elements.Abonne;
import server.elements.Documents.Document;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
*/
//class MediathequeTest {
//    ArrayList<Abonne> lstAbonne;
//    ArrayList<Document> lstDocument;
//    Mediatheque mediatheque;
//
//    @BeforeEach
//    void init() {
//        this.lstAbonne = new ArrayList<>();
//        this.lstDocument = new ArrayList<>();
//
//        lstAbonne.add(new Abonne(1, "Jean Dupont", "1990-05-15"));
//        lstAbonne.add(new Abonne(2, "Marie Martin", "1985-10-20"));
//        lstAbonne.add(new Abonne(3, "Pierre Durand", "1978-03-07"));
//
//        lstDocument.add(new Document(101, "Le Petit Prince"));
//        lstDocument.add(new Document(102, "Harry Potter and the Philosopher's Stone"));
//        lstDocument.add(new Document(103, "The Shawshank Redemption"));
//
//        mediatheque = new Mediatheque(lstAbonne, lstDocument);
//    }
//
//    @Test
//    void estUnAbonne() {
//        assertTrue(mediatheque.estUnAbonne(lstAbonne.get(0)));
//        assertFalse(mediatheque.estUnAbonne(new Abonne(50, "Jean", "1990-05-26")));
//    }
//
//    @Test
//    void estUnDocument() {
//        assertTrue(mediatheque.estUnDocument(lstDocument.get(0)));
//        assertFalse(mediatheque.estUnDocument(new Document(150, "The Redmpt")));
//    }
//
//    @Test
//    void setReservEmprunt() {
//        mediatheque.setReservEmprunt(lstDocument.get(0), lstAbonne.get(0));
//
//        assertTrue(lstDocument.get(0).estReserve());
//        assertFalse(lstDocument.get(0).estEmprunte());
//
//        assertThrows(ReservationException.class, () -> mediatheque.setReservEmprunt(lstDocument.get(0), lstAbonne.get(1)));
//    }
//}