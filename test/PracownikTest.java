import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

class PracownikTest {
    @Test
    void sprawdzeniePoprawnosciKonstruktora() {
        Pracownik pracownik = new Pracownik("Jan", "Nowak", "1","2");
        assertEquals("Jan", pracownik.getImie());
        assertEquals("Nowak", pracownik.getNazwisko());
        assertEquals("1", pracownik.getId());
        assertEquals("2", pracownik.getHaslo());
    }
}