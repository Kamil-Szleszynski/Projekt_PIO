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

    @Test
    void sprawdzenieEquals(){
        Pracownik pracownik1 = new Pracownik("Jan", "Nowak", "1","1241");
        Pracownik pracownik2 = new Pracownik("Jan", "Nowak", "2","4234");
        Pracownik pracownik3 = new Pracownik("Jan", "Nowak", "1","2433");
        assertTrue(pracownik1.equals(pracownik3));
        assertFalse(pracownik1.equals(pracownik2));
    }

}