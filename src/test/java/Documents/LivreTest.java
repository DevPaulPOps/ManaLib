/** package Documents;

import server.Exception.EmpruntException;
import server.Exception.ReservationException;
import server.Exception.RetourException;
import server.elements.Abonne;
import server.elements.Documents.Livre;

import static org.junit.jupiter.api.Assertions.*;



class LivreTest {

    @org.junit.jupiter.api.Test
    void changeState() {
        Livre l = new Livre(1, "paulLeBg");
        assertTrue(l.estRetourne());
    }

    @org.junit.jupiter.api.Test
    void getState() {
        Livre l = new Livre(1, "paulLeBg");
        Abonne ab = new Abonne(1, "paul", "27/01/5555");
        l.reservation(ab);
        assertTrue(l.estReserve());
        assertFalse(l.estRetourne());

        l.retour();
        assertTrue(l.estRetourne());

        l.emprunt(ab);
        assertTrue(l.estEmprunte());
    }

    @org.junit.jupiter.api.Test
    void throwException() {
        Livre l = new Livre(1, "paulLeBg");
        Abonne ab = new Abonne(1, "paul", "27/01/5555");

        assertThrows(RetourException.class, () -> l.retour());

        l.emprunt(ab);
        assertThrows(EmpruntException.class, () -> l.emprunt(ab));

        //Peut pas reservé si deja emprunté
        assertThrows(EmpruntException.class, () -> l.reservation(ab));

        l.retour();
        l.reservation(ab);
        assertThrows(ReservationException.class, () -> l.reservation(ab));
    }

    @org.junit.jupiter.api.Test
    void plusieursAbonne() {
        Livre l = new Livre(1, "paulLeBg");
        Abonne ab = new Abonne(1, "paul", "27/01/5555");
        Abonne abb = new Abonne(2, "owen", "27/01/5555");

        l.emprunt(ab);
        assertThrows(EmpruntException.class, () -> l.emprunt(abb));
        assertThrows(ReservationException.class, () -> l.reservation(abb));

        l.retour();
        l.reservation(abb);
        assertThrows(ReservationException.class, () -> l.reservation(ab));
        assertThrows(EmpruntException.class, () -> l.emprunt(ab));
    }
} */