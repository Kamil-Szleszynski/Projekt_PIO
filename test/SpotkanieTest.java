import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SpotkanieTest {

    @Test
    void sprawdzanieKonstruktora() {
        LocalDateTime oczekiwanaData = LocalDateTime.of(2026, 6, 15, 12, 0);
        Spotkanie spotkanie = new Spotkanie(oczekiwanaData,"Spotkanie testowe");
        assertEquals(oczekiwanaData, spotkanie.getCzasSpotkania());
        assertEquals("Spotkanie testowe",spotkanie.getNazwaSpotkania());
    }
}