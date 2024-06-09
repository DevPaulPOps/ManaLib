package server.elements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class AbonneTest {

    private Abonne abonne;
    private Abonne abonne2;

    @BeforeEach
    void setUp() {
        abonne = new Abonne("NomTest", new Date());
        abonne2 = new Abonne(1, "NomTest", new Date());
    }

    @Test
    void testGetIdAbonne() {
        assertNull(abonne.getEntityId());
    }

    @Test
    void testGetIdAbonne2() {
        assertEquals(1, abonne2.getEntityId());
    }

    @Test
    void testGetNom() {
        assertEquals("NomTest", abonne.getNom());
    }

    @Test
    void testGetDateDeNaissance() {
        assertNotNull(abonne.getDateDeNaissance());
    }

    @Test
    void testToString() {
        String expected = "null  NomTest  " + abonne.getDateDeNaissance();
        assertEquals(expected, abonne.toString());
    }
}