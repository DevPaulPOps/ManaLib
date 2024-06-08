package server.elements.Documents;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.Exception.NotAdultException;
import server.Exception.ReservationException;
import server.elements.Abonne;
import server.stateConstante.StateConstante;
import server.elements.interfaces.Abonnes;

import java.util.Calendar;
import java.util.Date;

class DVDTest {

    private DVD dvd;
    private Abonnes abonneAdulte;
    private Abonnes abonneMineur;

    @BeforeEach
    void setUp() {
        dvd = new DVD(1,"TitreDVD", StateConstante.RETOURNE, null, true);

        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 1);
        Date dateNaissanceAdulte = cal.getTime();
        abonneAdulte = new Abonne(1, "Adulte", dateNaissanceAdulte);

        cal.set(2023, Calendar.JANUARY, 1);
        Date dateNaissanceMineur = cal.getTime();
        abonneMineur = new Abonne(2, "Mineur", dateNaissanceMineur);
    }

    @Test
    void testReservationNotAdult() {
        assertThrows(NotAdultException.class, () -> {
            dvd.reservation(abonneMineur);
        });
    }

    @Test
    void testReservationAdult() throws ReservationException, NotAdultException {
        dvd.reservation(abonneAdulte);

        assertEquals(StateConstante.RESERVE, dvd.getState());
        assertEquals(1, dvd.getAbonneId());
    }
}
