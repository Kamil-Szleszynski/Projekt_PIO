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
    @Test
    void sprawdzenieDodaniaUczestnika(){
        LocalDateTime data = LocalDateTime.of(2026, 6, 15, 14, 30);
        Spotkanie spotkanie = new Spotkanie(data, "Daily Scrum");
        Pracownik pracownik1 = new Pracownik("Jan", "Nowak", "1","12234241");
        Pracownik pracownik2 = new Pracownik("Jan", "Nowak", "2","4223534");
        Pracownik pracownik3 = new Pracownik("Adam", "Nowak", "2","42321534");
        Pracownik pracownik4 = new Pracownik("Jakub", "Kowalski", "4","4233214");

        assertEquals(true,spotkanie.addUczestnik(pracownik1));
        assertEquals(true,spotkanie.addUczestnik(pracownik2));
        assertEquals(false,spotkanie.addUczestnik(pracownik3));
        assertEquals(true,spotkanie.addUczestnik(pracownik4));

    }

    @Test
    void sprawdzenieOrganizatora(){
        LocalDateTime data = LocalDateTime.of(2026, 6, 15, 14, 30);
        Spotkanie spotkanie = new Spotkanie(data, "Daily Scrum");
        Pracownik pracownik1 = new Pracownik("Jan", "Nowak", "1","12234241");

        assertEquals(null,spotkanie.getOrganizator());

        spotkanie.setOrganizator(pracownik1);

        assertEquals(pracownik1,spotkanie.getOrganizator());
    }

    @Test
    void sprawdzenieDodawaniaMiejcs(){
        LocalDateTime data = LocalDateTime.of(2026, 6, 15, 14, 30);
        Spotkanie spotkanie = new Spotkanie(data, "Daily Scrum");
        Sala salaE1 = new Sala("E1",30);
        spotkanie.setSala(salaE1);

        for(int i = 0;i<30;i++){
            assertEquals(i+1,spotkanie.getMiejsca().get(i).getNumerMiejsca());
        }
    }
}