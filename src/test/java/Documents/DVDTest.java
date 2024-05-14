package Documents;

import server.elements.Documents.DVD;
import server.elements.Documents.Livre;

import static org.junit.jupiter.api.Assertions.*;

class DVDTest {
    @org.junit.jupiter.api.Test
    void bonneValeur() {
        DVD d = new DVD(1,"paulLeBg", true);
        assertTrue(d.estContenuAdulte());
        assertEquals(1,d.getNumero());
        assertEquals("paulLeBg",d.getTitre());

        Livre l = new Livre(59,"yaaaaaaaa");
        assertEquals(59,l.getNumero());
        assertEquals("yaaaaaaaa",l.getTitre());
    }
}