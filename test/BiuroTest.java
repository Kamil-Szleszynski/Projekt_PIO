import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BiuroTest {
    @Test
    void sprawdzenieTworzeniaBiura() {
        Lokalizacja lok = new Lokalizacja("Kraków");
        Biuro biuro = new Biuro(lok);

        assertEquals(lok, biuro.getLokalizacja());
        assertNotNull(biuro.getSale());
        assertTrue(biuro.getSale().isEmpty());
    }
}