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
    @Test
    void sprawdzanieDodawaniaSaliDoSpotkania() {
        LocalDateTime data = LocalDateTime.of(2026, 6, 15, 14, 30);
        Spotkanie spotkanie = new Spotkanie(data, "Daily Scrum");
        Sala sala = new Sala("F104", 30);
        spotkanie.setSala(sala);
        assertEquals("14:30", spotkanie.getGodzinaRozpoczecia());    }
}