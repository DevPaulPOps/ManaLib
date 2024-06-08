package server.elements.Documents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import server.Exception.EmpruntException;
import server.Exception.ReservationException;
import server.Exception.RetourException;
import server.elements.Abonne;
import server.elements.interfaces.Abonnes;
import server.stateConstante.StateConstante;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DocumentTest {

    private Document document;
    private Abonnes abonne;

    @BeforeEach
    void setUp() {
        document = new Document(1,"TitreTest", StateConstante.RETOURNE, null);
        abonne = new Abonne(1, "NomTest", new Date());
    }

    @Test
    void testReservation() throws ReservationException {
        document.reservation(abonne);

        assertEquals(StateConstante.RESERVE, document.getState());
        assertEquals(1, document.getAbonneId());

        assertThrows(ReservationException.class, () -> {
            document.reservation(abonne);
        });

        document.retour();
    }

    @Test
    void testEmprunt() throws EmpruntException {
        document.emprunt(abonne);
        assertEquals(StateConstante.EMPRUNTE, document.getState());
        assertEquals(1, document.getAbonneId());

        assertThrows(EmpruntException.class, () -> {
            document.emprunt(abonne);
        });

        document.retour();
    }

    @Test
    void testRetour() throws RetourException {
        document.reservation(abonne);
        document.retour();
        assertEquals(StateConstante.RETOURNE, document.getState());
        assertNull(document.getAbonneId());

        assertThrows(RetourException.class, () -> {
            document.retour();
        });
    }
}