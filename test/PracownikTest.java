import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PracownikTest {
    @Test
    void sprawdzeniePoprawnosciKonstruktora(){
        Pracownik pracownik = new Pracownik("Jan","Nowak","1");
        assertEquals("Jan",pracownik.getImie());
        assertEquals("Nowak",pracownik.getNazwisko());
        assertEquals("1",pracownik.getId());
    }
}