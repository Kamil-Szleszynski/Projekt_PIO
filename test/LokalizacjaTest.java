import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LokalizacjaTest {
    @Test
    void sprawdzenieKonstruktoraILokalizacji() {
        Lokalizacja lokalizacja = new Lokalizacja("Warszawa, ul. Wiejska 4");
        assertEquals("Warszawa, ul. Wiejska 4", lokalizacja.getAdres());
    }
}