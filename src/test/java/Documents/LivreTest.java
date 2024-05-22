/**
 * package Documents;
 * <p>
 * import server.Exception.EmpruntException;
 * import server.Exception.ReservationException;
 * import server.Exception.RetourException;
 * import server.elements.Abonne;
 * import server.elements.Documents.Livre;
 * <p>
 * import static org.junit.jupiter.api.Assertions.*;
 * <p>
 * <p>
 * <p>
 * class LivreTest {
 *
 * @org.junit.jupiter.api.Test void changeState() {
 * Livre l = new Livre(1, "paulLeBg");
 * assertTrue(l.estRetourne());
 * }
 * @org.junit.jupiter.api.Test void getState() {
 * Livre l = new Livre(1, "paulLeBg");
 * Abonne ab = new Abonne(1, "paul", "27/01/5555");
 * l.reservation(ab);
 * assertTrue(l.estReserve());
 * assertFalse(l.estRetourne());
 * <p>
 * l.retour();
 * assertTrue(l.estRetourne());
 * <p>
 * l.emprunt(ab);
 * assertTrue(l.estEmprunte());
 * }
 * @org.junit.jupiter.api.Test void throwException() {
 * Livre l = new Livre(1, "paulLeBg");
 * Abonne ab = new Abonne(1, "paul", "27/01/5555");
 * <p>
 * assertThrows(RetourException.class, () -> l.retour());
 * <p>
 * l.emprunt(ab);
 * assertThrows(EmpruntException.class, () -> l.emprunt(ab));
 * <p>
 * //Peut pas reservé si deja emprunté
 * assertThrows(EmpruntException.class, () -> l.reservation(ab));
 * <p>
 * l.retour();
 * l.reservation(ab);
 * assertThrows(ReservationException.class, () -> l.reservation(ab));
 * }
 * @org.junit.jupiter.api.Test void plusieursAbonne() {
 * Livre l = new Livre(1, "paulLeBg");
 * Abonne ab = new Abonne(1, "paul", "27/01/5555");
 * Abonne abb = new Abonne(2, "owen", "27/01/5555");
 * <p>
 * l.emprunt(ab);
 * assertThrows(EmpruntException.class, () -> l.emprunt(abb));
 * assertThrows(ReservationException.class, () -> l.reservation(abb));
 * <p>
 * l.retour();
 * l.reservation(abb);
 * assertThrows(ReservationException.class, () -> l.reservation(ab));
 * assertThrows(EmpruntException.class, () -> l.emprunt(ab));
 * }
 * }
 */